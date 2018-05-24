package dao.iml;

import dao.ContactDao;
import dao.base.BaseDao;
import entity.Contact;
import exception.ContactNoExistException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Package: dao.iml
 * FileName: ContactDaoByMySQLImpl
 * Date: on 2018/5/21  下午2:52
 * Auther: Wally
 * Descirbe:联系人Dao层MySql实现类
 */
public class ContactDaoByMySQLImpl extends BaseDao implements ContactDao {
    @Override
    public boolean addContact(Contact contact) {
        Connection connection = JdbcUtil.getConnection();
        String sql = "INSERT INTO contact(NAME,gender,age,phone,email,qq) VALUES( ?, ?, ?, ?, ?, ?)";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(connection, sql, contact.getName(), contact.getGender(), contact.getAge(), contact.getPhone(), contact.getEmail(), contact.getQq());
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }
        return false;
    }

    @Override
    public boolean updateContact(Contact contact) {
        Connection connection = JdbcUtil.getConnection();
        String sql = "UPDATE contact SET NAME= ? ,gender= ?,age= ?,phone= ?,email= ?,qq= ? WHERE id= ?";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(connection, sql, contact.getName(), contact.getGender(), contact.getAge(), contact.getPhone(), contact.getEmail(), contact.getQq(), contact.getId());
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }
        return false;
    }

    @Override
    public boolean deleteContact(String id) {
        Connection connection = JdbcUtil.getConnection();
        String sql = "DELETE FROM contact WHERE id=?";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(connection, sql, id);
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }
        return false;
    }

    @Override
    public Contact findContactById(String id) throws ContactNoExistException {
        Connection connection = JdbcUtil.getConnection();
        String sql = "SELECT * FROM contact WHERE id = ?";
        QueryRunner runner = new QueryRunner();
        Contact contact = null;
        try {
            contact = runner.query(connection, sql, new BeanHandler<Contact>(Contact.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
        if (contact == null) {
            throw new ContactNoExistException();
        }
        return contact;
    }

    @Override
    public Contact findContactByName(String contactName) throws ContactNoExistException {
        Connection connection = JdbcUtil.getConnection();
        String sql = "select * from contact where name = ?";
        QueryRunner runner = new QueryRunner();
        Contact contact;
        try {
            contact = runner.query(connection, sql, new BeanHandler<Contact>(Contact.class), contactName);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
        if (contact == null) {
            throw new ContactNoExistException();
        }
        return contact;
    }

    @Override
    public List<Contact> findAllContact() {
        String sql = "SELECT * FROM contact";
        Connection connection = JdbcUtil.getConnection();
        QueryRunner runner = new QueryRunner();
        List<Contact> allContactList;
        try {
            allContactList = runner.query(connection, sql, new BeanListHandler<Contact>(Contact.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
        return allContactList;
    }

    @Override
    public boolean checkContactIsExist(Contact contact) {
        Connection connection = JdbcUtil.getConnection();
        String sql = "SELECT id FROM contact WHERE name = ?";
        QueryRunner runner = new QueryRunner();
        Integer id;
        try {
            id = runner.query(connection, sql, new ScalarHandler<Integer>(), contact.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
        return id != null;
    }
}