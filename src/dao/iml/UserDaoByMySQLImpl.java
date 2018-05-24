package dao.iml;

import dao.UserDao;
import dao.base.BaseDao;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Package: dao.iml
 * FileName: UserDaoByMySQLImpl
 * Date: on 2018/5/22  下午5:06
 * Auther: Wally
 * Descirbe:UserDao的MySql实现
 */
public class UserDaoByMySQLImpl extends BaseDao implements UserDao {
    @Override
    public boolean addUser(User user) {
        Connection connection = JdbcUtil.getConnection();
        String sql = "INSERT INTO user(userName, pwd) VALUES(?, ?)";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(JdbcUtil.getConnection(), sql, user.getUserName(), user.getPwd());
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        Connection connection = JdbcUtil.getConnection();
        String sql = "DELETE FROM user WHERE id = ?";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(JdbcUtil.getConnection(), sql, userId);
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }
        return false;
    }

    @Override
    public User findById(String userId) {
        Connection connection = JdbcUtil.getConnection();
        String sql = "SELECT * FROM USER WHERE id = ?";
        QueryRunner runner = new QueryRunner();
        User user = null;
        try {
            user = runner.query(JdbcUtil.getConnection(), sql, new BeanHandler<User>(User.class), userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        Connection connection = JdbcUtil.getConnection();
        String sql = "UPDATE USER SET userName = ?, pwd = ? WHERE id = ?";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(JdbcUtil.getConnection(), sql, user.getUserName(), user.getPwd(), user.getId());
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }
        return false;
    }

    @Override
    public boolean checkUserIsExist(User user) {
        Connection connection = JdbcUtil.getConnection();
        String sql = "SELECT id FROM USER WHERE userName = ?";
        QueryRunner runner = new QueryRunner();
        try {
            Integer result = runner.query(connection, sql, new ScalarHandler<Integer>(), user.getUserName());
            //不等于空就是之前没有过
            return result != null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }
        return false;
    }
}