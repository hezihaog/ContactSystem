package dao.iml;

import dao.ContactDao;
import dao.base.BaseDao;
import entity.Contact;
import exception.ContactNoExistException;

import java.util.ArrayList;

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
        String sql = "INSERT INTO contact(NAME,gender,age,phone,email,qq) VALUES( ?, ?, ?, ?, ?, ?)";
        Object[] paramsValue = {contact.getName(), contact.getGender(), contact.getAge(), contact.getPhone(), contact.getEmail(), contact.getQq()};
        return update(sql, paramsValue);
    }

    @Override
    public boolean updateContact(Contact contact) {
        String sql = "UPDATE contact SET NAME= ? ,gender= ?,age= ?,phone= ?,email= ?,qq= ? WHERE id= ?";
        Object[] paramsValue = {contact.getName(), contact.getGender(), contact.getAge(), contact.getPhone(), contact.getEmail(), contact.getQq(), contact.getId()};
        return update(sql, paramsValue);
    }

    @Override
    public boolean deleteContact(String id) {
        String sql = "DELETE FROM contact WHERE id=?";
        Object[] paramsValue = {id};
        return update(sql, paramsValue);
    }

    @Override
    public Contact findContactById(String id) throws ContactNoExistException {
        String sql = "SELECT * FROM contact WHERE id = ?";
        Object[] paramsValue = {id};
        Contact contact = querySingle(sql, paramsValue, Contact.class);
        if (contact == null) {
            throw new ContactNoExistException();
        }
        return contact;
    }

    @Override
    public Contact findContactByName(String contactName) throws ContactNoExistException {
        String sql = "select * from contact where name = ?";
        Object[] paramsValue = {contactName};
        Contact contact = querySingle(sql, paramsValue, Contact.class);
        if (contact == null) {
            throw new ContactNoExistException();
        }
        return contact;
    }

    @Override
    public ArrayList<Contact> findAllContact() {
        String sql = "SELECT * FROM contact";
        return query(sql, null, Contact.class);
    }

    @Override
    public boolean checkContactIsExist(Contact contact) {
        String sql = "SELECT * FROM contact WHERE name = ?";
        Object[] paramsValue = {contact.getName()};
        return checkIsExist(sql, paramsValue);
    }
}