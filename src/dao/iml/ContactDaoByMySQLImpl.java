package dao.iml;

import dao.ContactDao;
import entity.Contact;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Package: dao.iml
 * FileName: ContactDaoByMySQLImpl
 * Date: on 2018/5/21  下午2:52
 * Auther: Wally
 * Descirbe:联系人Dao层MySql实现类
 */
public class ContactDaoByMySQLImpl implements ContactDao {
    @Override
    public boolean addContact(Contact contact) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement("INSERT INTO contact(NAME,gender,age,phone,email,qq) VALUES( ?, ?, ?, ?, ?, ?)");
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getGender());
            statement.setInt(3, contact.getAge());
            statement.setString(4, contact.getPhone());
            statement.setString(5, contact.getEmail());
            statement.setString(6, contact.getQq());
            int affectedNum = statement.executeUpdate();
            return affectedNum > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement);
        }
    }

    @Override
    public boolean updateContact(Contact contact) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "UPDATE contact SET NAME= ? ,gender= ?,age= ?,phone= ?,email= ?,qq= ? WHERE id= ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getGender());
            statement.setInt(3, contact.getAge());
            statement.setString(4, contact.getPhone());
            statement.setString(5, contact.getEmail());
            statement.setString(6, contact.getQq());
            statement.setString(7, contact.getId());
            int affectedNum = statement.executeUpdate();
            return affectedNum > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement);
        }
    }

    @Override
    public boolean deleteContact(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement("DELETE FROM contact WHERE id=?");
            statement.setInt(1, Integer.valueOf(id));
            int affectedNum = statement.executeUpdate();
            return affectedNum > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement);
        }
    }

    @Override
    public Contact findContactById(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM contact WHERE id = ?");
            statement.setInt(1, Integer.valueOf(id));
            resultSet = statement.executeQuery();
            Contact contact = null;
            if (resultSet.next()) {
                contact = new Contact();
                contact.setId(resultSet.getString("id"));
                contact.setName(resultSet.getString("name"));
                contact.setGender(resultSet.getString("gender"));
                contact.setAge(resultSet.getInt("age"));
                contact.setPhone(resultSet.getString("phone"));
                contact.setEmail(resultSet.getString("email"));
                contact.setQq(resultSet.getString("qq"));
            }
            return contact;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement, resultSet);
        }
    }

    @Override
    public ArrayList<Contact> findAllContact() {
        ArrayList<Contact> allList = new ArrayList<Contact>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM contact");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getString("id"));
                contact.setName(resultSet.getString("name"));
                contact.setGender(resultSet.getString("gender"));
                contact.setAge(resultSet.getInt("age"));
                contact.setPhone(resultSet.getString("phone"));
                contact.setEmail(resultSet.getString("email"));
                contact.setQq(resultSet.getString("qq"));
                allList.add(contact);
            }
            return allList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement, resultSet);
        }
    }
}