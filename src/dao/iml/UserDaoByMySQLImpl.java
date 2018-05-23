package dao.iml;

import dao.UserDao;
import dao.base.BaseDao;
import entity.User;

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
        String sql = "INSERT INTO user(userName, pwd) VALUES(?, ?)";
        Object[] paramsValue = {user.getUserName(), user.getPwd()};
        return update(sql, paramsValue);
    }

    @Override
    public boolean deleteUser(String userId) {
        String sql = "DELETE FROM user WHERE id = ?";
        Object[] paramsValue = {userId};
        return update(sql, paramsValue);
    }

    @Override
    public User findById(String userId) {
        String sql = "SELECT * FROM USER WHERE id = ?";
        Object[] paramsValue = {userId};
        return querySingle(sql, paramsValue, User.class);
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE USER SET userName = ?, pwd = ? WHERE id = ?";
        Object[] paramsValue = {user.getUserName(), user.getPwd(), user.getId()};
        return update(sql, paramsValue);
    }

    @Override
    public boolean checkUserIsExist(User user) {
        String sql = "SELECT * FROM USER WHERE userName = ?";
        Object[] paramsValue = {user.getUserName()};
        return checkIsExist(sql, paramsValue);
    }
}
