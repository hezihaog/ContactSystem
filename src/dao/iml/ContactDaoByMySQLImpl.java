package dao.iml;

import constant.ContactSystemConstant;
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
import util.TextUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Package: dao.iml
 * FileName: ContactDaoByMySQLImpl
 * Date: on 2018/5/21  下午2:52
 * Auther: Wally
 * Descirbe:联系人Dao层MySql实现类
 */
public class ContactDaoByMySQLImpl extends BaseDao<Contact> implements ContactDao {
    @Override
    protected String initTabName() {
        return "contact";
    }

    /**
     * 获得要查询出来的字段
     */
    private String getContactMainColumn() {
        return "contact.userId as 'userId', contact.id, contact.name, contact.gender, contact.age, contact.phone, contact.email, contact.qq";
    }

    @Override
    public boolean add(Contact contact) {
        StringBuilder parmas = new StringBuilder(" (");
        StringBuilder values = new StringBuilder(" VALUES(");
        ArrayList<Object> paramsList = new ArrayList<Object>();
        //拼接要插入的字段
        if (!TextUtil.isEmpty(contact.getUserId())) {
            parmas.append("userId,");
            values.append("?,");
            paramsList.add(contact.getUserId());
        }
        if (!TextUtil.isEmpty(contact.getName())) {
            parmas.append("name,");
            values.append("?,");
            paramsList.add(contact.getName());
        }
        if (!TextUtil.isEmpty(contact.getGender())) {
            parmas.append("gender,");
            values.append("?,");
            paramsList.add(contact.getGender());
        }
        if (!(contact.getAge() == -1)) {
            parmas.append("age,");
            values.append("?,");
            paramsList.add(contact.getAge());
        }
        if (!TextUtil.isEmpty(contact.getPhone())) {
            parmas.append("phone,");
            values.append("?,");
            paramsList.add(contact.getPhone());
        }
        if (!TextUtil.isEmpty(contact.getEmail())) {
            parmas.append("email,");
            values.append("?,");
            paramsList.add(contact.getEmail());
        }
        if (!TextUtil.isEmpty("qq")) {
            parmas.append("qq,");
            values.append("?,");
            paramsList.add(contact.getQq());
        }
        parmas = parmas.deleteCharAt(parmas.length() - 1);
        values = values.deleteCharAt(values.length() - 1);
        parmas.append(")");
        values.append(")");
        String sql = "INSERT INTO " + getTabName() + parmas.toString() + values.toString();
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(JdbcUtil.getConnection(), sql, paramsList.toArray());
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Contact contact) {
        String sql = "UPDATE " + getTabName() + " SET NAME= ? ,gender= ?,age= ?,phone= ?,email= ?,qq= ? WHERE userId = ? and id= ?";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(JdbcUtil.getConnection(), sql, contact.getName(), contact.getGender(),
                    contact.getAge(), contact.getPhone(), contact.getEmail(), contact.getQq(), contact.getUserId(), contact.getId());
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String userId, String contactId) {
        String sql = "DELETE FROM " + getTabName() + " WHERE userId = ? and id = ?";
        QueryRunner runner = new QueryRunner();
        try {
            int num = runner.update(JdbcUtil.getConnection(), sql, userId, contactId);
            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteList(String userId, String[] ids) {
        for (String id : ids) {
            delete(userId, id);
        }
        return true;
    }

    @Override
    public Contact findById(String userId, String contactId) throws ContactNoExistException {
        String sql = "SELECT " + getContactMainColumn() + " FROM " + getTabName() + " WHERE userId = ? and id = ?";
        QueryRunner runner = new QueryRunner();
        Contact contact;
        try {
            contact = runner.query(JdbcUtil.getConnection(), sql, new BeanHandler<Contact>(Contact.class), userId, contactId);
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
    public Contact findByName(String userId, String contactName) throws ContactNoExistException {
        String sql = "SELECT " + getContactMainColumn() + " FROM " + getTabName() + " WHERE userId = ? and name = ?";
        QueryRunner runner = new QueryRunner();
        Contact contact;
        try {
            contact = runner.query(JdbcUtil.getConnection(), sql, new BeanHandler<Contact>(Contact.class), userId, contactName);
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
        String sql = "SELECT id FROM " + getTabName() + " WHERE userId = ? and name = ?";
        QueryRunner runner = new QueryRunner();
        Integer id;
        try {
            id = runner.query(JdbcUtil.getConnection(), sql, new ScalarHandler<Integer>(), contact.getUserId(), contact.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return id != null;
    }

    @Override
    protected List<Contact> onFindAllWithPage(Map<String, Object> args, PageMsg pageMsg) {
        String sql = "SELECT " + getContactMainColumn() + " FROM " + getTabName() + " left outer join my_user on my_user.id = contact.userId where contact.userId = ? LIMIT ?, ?";
        QueryRunner runner = new QueryRunner();
        try {
            String userId = (String) args.get(ContactSystemConstant.DataKey.KEY_USER_ID);
            return runner.query(JdbcUtil.getConnection(), sql, new BeanListHandler<Contact>(Contact.class), userId, pageMsg.getStartIndex(), pageMsg.getCount());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contact> findAll(Map<String, Object> args) {
        String sql = "SELECT " + getContactMainColumn() + " FROM " + getTabName() + " left outer join my_user on my_user.id = contact.userId where contact.userId = ?";
        QueryRunner runner = new QueryRunner();
        List<Contact> allContactList;
        try {
            String userId = (String) args.get(ContactSystemConstant.DataKey.KEY_USER_ID);
            allContactList = runner.query(JdbcUtil.getConnection(), sql, new BeanListHandler<Contact>(Contact.class), userId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return allContactList;
    }
}