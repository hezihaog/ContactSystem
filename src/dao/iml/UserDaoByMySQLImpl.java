package dao.iml;

import dao.UserDao;
import entity.User;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Package: dao.iml
 * FileName: UserDaoByMySQLImpl
 * Date: on 2018/5/22  下午5:06
 * Auther: Wally
 * Descirbe:UserDao的MySql实现
 */
public class UserDaoByMySQLImpl implements UserDao {
    @Override
    public boolean addUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement("INSERT INTO user(userName, pwd) VALUES(?, ?)");
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPwd());
            int affectedNum = statement.executeUpdate();
            return affectedNum > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement);
        }
    }

    @Override
    public boolean deleteUser(String userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement("delete from user where id = ?");
            statement.setString(1, userId);
            int affectedNum = statement.executeUpdate();
            return affectedNum > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement);
        }
    }

    @Override
    public User findById(String userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM USER WHERE id = ?");
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getString("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPwd(resultSet.getString("pwd"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement);
        }
    }

    @Override
    public boolean updateUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement("UPDATE USER SET userName = ?, pwd = ? WHERE id = ?");
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPwd());
            statement.setString(3, user.getId());
            int affectedNum = statement.executeUpdate();
            return affectedNum > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement);
        }
    }

    @Override
    public boolean checkUserIsExist(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM USER WHERE userName = ?");
            statement.setString(1, user.getUserName());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement);
        }
    }
}
