package dao.iml;

import constant.ContactSystemConstant;
import dao.ContactDao;
import dao.base.BaseDao;
import dao.base.PageMsg;
import entity.Contact;
import exception.ContactNoExistException;
import org.apache.ibatis.session.SqlSession;
import util.MybatisHelper;

import java.util.*;

/**
 * Package: dao.iml
 * FileName: ContactDaoByMyBatisImpl
 * Date: on 2018/6/1  下午4:55
 * Auther: Wally
 * Descirbe:联系人增删查改，MyBatis实现
 */
public class ContactDaoByMyBatisImpl extends BaseDao<Contact> implements ContactDao {
    @Override
    protected String initTabName() {
        return "contact";
    }

    /**
     * 获取方法对应的MyBatis命名空间
     *
     * @param funcName 要调用的方法
     */
    private String getMyBatisNameSpace(String funcName) {
        return Contact.class.getName() + "." + funcName;
    }

    @Override
    public boolean add(Contact contact) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            int row = sqlSession.insert(getMyBatisNameSpace("add"), contact);
            if (row > 0) {
                sqlSession.commit();
                return true;
            } else {
                sqlSession.rollback();
            }
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            MybatisHelper.getInstance().closeSqlSession();
        }
        return false;
    }

    @Override
    public boolean update(Contact contact) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("userId", contact.getUserId());
            map.put("id", contact.getId());
            map.put("name", contact.getName());
            map.put("gender", contact.getGender());
            map.put("age", contact.getAge());
            map.put("phone", contact.getPhone());
            map.put("email", contact.getEmail());
            map.put("qq", contact.getQq());
            int row = sqlSession.update(getMyBatisNameSpace("update"), map);
            if (row > 0) {
                sqlSession.commit();
                return true;
            } else {
                sqlSession.rollback();
            }
        } catch (Exception e) {
            //异常回滚
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            MybatisHelper.getInstance().closeSqlSession();
        }
        return false;
    }

    @Override
    public boolean delete(String userId, String contactId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(ContactSystemConstant.DataKey.KEY_USER_ID, userId);
            map.put(ContactSystemConstant.DataKey.KEY_CONTACT_ID, contactId);
            int row = sqlSession.delete(getMyBatisNameSpace("delete"), map);
            if (row > 0) {
                sqlSession.commit();
                return true;
            } else {
                sqlSession.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            MybatisHelper.getInstance().closeSqlSession();
        }
        return false;
    }

    @Override
    public boolean deleteList(String userId, String[] ids) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            for (String id : ids) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put(ContactSystemConstant.DataKey.KEY_USER_ID, userId);
                map.put(ContactSystemConstant.DataKey.KEY_CONTACT_ID, id);
                sqlSession.delete(getMyBatisNameSpace("delete"), map);
            }
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            MybatisHelper.getInstance().closeSqlSession();
        }
        return false;
    }

    @Override
    public Contact findById(String userId, String contactId) throws ContactNoExistException {
        SqlSession sqlSession = null;
        Contact contact = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(ContactSystemConstant.DataKey.KEY_USER_ID, userId);
            map.put(ContactSystemConstant.DataKey.KEY_CONTACT_ID, contactId);
            contact = sqlSession.selectOne(getMyBatisNameSpace("findById"), map);
            if (contact == null) {
                throw new ContactNoExistException();
            }
            return contact;
        } catch (ContactNoExistException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisHelper.getInstance().closeSqlSession();
        }
        return null;
    }

    @Override
    public Contact findByName(String userId, String contactName) throws ContactNoExistException {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put(ContactSystemConstant.DataKey.KEY_USER_ID, userId);
            map.put(ContactSystemConstant.DataKey.KEY_CONTACT_NAME, contactName);
            Contact contact = sqlSession.selectOne(getMyBatisNameSpace("findByName"), map);
            if (contact == null) {
                throw new ContactNoExistException();
            }
            return contact;
        } catch (ContactNoExistException e) {
            throw new ContactNoExistException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            MybatisHelper.getInstance().closeSqlSession();
        }
    }

    @Override
    public boolean checkIsExist(Contact contact) {
        SqlSession sqlSession = null;
        try {
            //查询指定的用户是否已经存在了这个联系人姓名
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            Integer id = sqlSession.selectOne(getMyBatisNameSpace("checkIsExist"), contact);
            if (id == null) {
                return false;
            }
            return id > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisHelper.getInstance().closeSqlSession();
        }
        return false;
    }

    @Override
    protected List<Contact> onFindAllWithPage(Map<String, Object> args, PageMsg pageMsg) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            Map<String, Object> map = new LinkedHashMap<String, Object>(args);
            map.put("startIndex", pageMsg.getStartIndex());
            map.put("count", pageMsg.getCount());
            List<Contact> allWithPage = sqlSession.selectList(getMyBatisNameSpace("onFindAllWithPage"), map);
            if (allWithPage != null) {
                return allWithPage;
            } else {
                return new ArrayList<Contact>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisHelper.getInstance().closeSqlSession();
        }
        return new ArrayList<Contact>();
    }

    @Override
    public List<Contact> findAll(Map<String, Object> args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            HashMap<String, Object> map = new HashMap<String, Object>(args);
            List<Contact> list = sqlSession.selectList(getMyBatisNameSpace("findAll"), map);
            if (list != null) {
                return list;
            } else {
                return new ArrayList<Contact>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisHelper.getInstance().closeSqlSession();
        }
        return new ArrayList<Contact>();
    }
}