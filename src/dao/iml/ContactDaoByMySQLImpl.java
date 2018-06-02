package dao.iml;

import dao.ContactDao;
import dao.base.BaseDao;
import dao.base.PageMsg;
import entity.Contact;
import exception.ContactNoExistException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Package: dao.iml
 * FileName: ContactDaoByMySQLImpl
 * Date: on 2018/5/21  下午2:52
 * Auther: Wally
 * Descirbe:联系人Dao层MySql实现类
 */
public class ContactDaoByMySQLImpl extends BaseDao<Contact> implements ContactDao {
    @Override
    public boolean add(Contact contact) {
        String sql = "INSERT INTO contact(NAME,gender,age,phone,email,qq) VALUES( ?, ?, ?, ?, ?, ?)";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(JdbcUtil.getConnection(), sql, contact.getName(), contact.getGender(), contact.getAge(), contact.getPhone(), contact.getEmail(), contact.getQq());
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Contact contact) {
        String sql = "UPDATE contact SET NAME= ? ,gender= ?,age= ?,phone= ?,email= ?,qq= ? WHERE id= ?";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(JdbcUtil.getConnection(), sql, contact.getName(), contact.getGender(), contact.getAge(), contact.getPhone(), contact.getEmail(), contact.getQq(), contact.getId());
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM contact WHERE id=?";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(JdbcUtil.getConnection(), sql, id);
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteList(String[] ids) {
        for (String id : ids) {
            delete(id);
        }
        return true;
    }

    @Override
    public Contact findById(String id) throws ContactNoExistException {
        String sql = "SELECT * FROM contact WHERE id = ?";
        QueryRunner runner = new QueryRunner();
        Contact contact;
        try {
            contact = runner.query(JdbcUtil.getConnection(), sql, new BeanHandler<Contact>(Contact.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if (contact == null) {
            throw new ContactNoExistException();
        }
        return contact;
    }

    @Override
    public Contact findByName(String contactName) throws ContactNoExistException {
        String sql = "SELECT * FROM contact WHERE name = ?";
        QueryRunner runner = new QueryRunner();
        Contact contact;
        try {
            contact = runner.query(JdbcUtil.getConnection(), sql, new BeanHandler<Contact>(Contact.class), contactName);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if (contact == null) {
            throw new ContactNoExistException();
        }
        return contact;
    }

    @Override
    public boolean checkIsExist(Contact contact) {
        String sql = "SELECT id FROM contact WHERE name = ?";
        QueryRunner runner = new QueryRunner();
        Integer id;
        try {
            id = runner.query(JdbcUtil.getConnection(), sql, new ScalarHandler<Integer>(), contact.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return id != null;
    }

    @Override
    protected List<Contact> onFindAllWithPage(PageMsg pageMsg) {
        String sql = "SELECT * FROM contact LIMIT ?, ?";
        QueryRunner runner = new QueryRunner();
        try {
            return runner.query(JdbcUtil.getConnection(), sql, new BeanListHandler<Contact>(Contact.class), pageMsg.getStartIndex(), pageMsg.getCount());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contact> findAll() {
        String sql = "SELECT * FROM contact";
        QueryRunner runner = new QueryRunner();
        List<Contact> allContactList;
        try {
            allContactList = runner.query(JdbcUtil.getConnection(), sql, new BeanListHandler<Contact>(Contact.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return allContactList;
    }
}