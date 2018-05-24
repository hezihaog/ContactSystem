package dao.iml;

import dao.UserDao;
import dao.base.BaseDao;
import dao.base.PageMsg;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Package: dao.iml
 * FileName: UserDaoByMySQLImpl
 * Date: on 2018/5/22  下午5:06
 * Auther: Wally
 * Descirbe:UserDao的MySql实现
 */
public class UserDaoByMySQLImpl extends BaseDao<User> implements UserDao {
    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO user(userName, pwd) VALUES(?, ?)";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(JdbcUtil.getConnection(), sql, user.getUserName(), user.getPwd());
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        String sql = "DELETE FROM user WHERE id = ?";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(JdbcUtil.getConnection(), sql, userId);
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findById(String userId) {
        String sql = "SELECT * FROM USER WHERE id = ?";
        QueryRunner runner = new QueryRunner();
        User user = null;
        try {
            user = runner.query(JdbcUtil.getConnection(), sql, new BeanHandler<User>(User.class), userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE USER SET userName = ?, pwd = ? WHERE id = ?";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(JdbcUtil.getConnection(), sql, user.getUserName(), user.getPwd(), user.getId());
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkUserIsExist(User user) {
        String sql = "SELECT id FROM USER WHERE userName = ?";
        QueryRunner runner = new QueryRunner();
        try {
            Integer result = runner.query(JdbcUtil.getConnection(), sql, new ScalarHandler<Integer>(), user.getUserName());
            //不等于空就是之前没有过
            return result != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected List<User> onFindAllWithPage(PageMsg pageMsg) {
        String sql = "SELECT * FROM user LIMIT ?, ?";
        QueryRunner runner = JdbcUtil.getQueryRunner();
        try {
            return runner.query(sql, new BeanListHandler<User>(User.class), pageMsg.getStartIndex(), pageMsg.getCount());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        QueryRunner runner = JdbcUtil.getQueryRunner();
        try {
            return runner.query(sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}