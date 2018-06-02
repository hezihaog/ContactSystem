package dao.iml;

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
            int row = sqlSession.insert(getMyBatisNameSpace("add"));
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
    public boolean delete(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            int row = sqlSession.delete(getMyBatisNameSpace("delete"), id);
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
    public boolean deleteList(String[] ids) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            int row = sqlSession.delete(getMyBatisNameSpace("deleteList"), ids);
            //批量删除，如果传入不存在的id，则不会删除到，直接无视了
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
    public Contact findById(String id) throws ContactNoExistException {
        SqlSession sqlSession = null;
        Contact contact = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            contact = sqlSession.selectOne(getMyBatisNameSpace("findById"), id);
            if (contact == null) {
                throw new ContactNoExistException();
            }
            return contact;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisHelper.getInstance().closeSqlSession();
        }
        return null;
    }

    @Override
    public Contact findByName(String contactName) throws ContactNoExistException {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("contactName", contactName);
            Contact contact = sqlSession.selectOne(getMyBatisNameSpace("findByName"), map);
            if (contact == null) {
                throw new ContactNoExistException();
            }
            return contact;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisHelper.getInstance().closeSqlSession();
        }
        return null;
    }

    @Override
    public boolean checkIsExist(Contact contact) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            sqlSession.selectOne(getMyBatisNameSpace("checkIsExist"), contact);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisHelper.getInstance().closeSqlSession();
        }
        return false;
    }

    @Override
    protected List<Contact> onFindAllWithPage(PageMsg pageMsg) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            Map<String, Object> map = new LinkedHashMap<String, Object>();
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
    public List<Contact> findAll() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisHelper.getInstance().getSqlSession();
            List<Contact> list = sqlSession.selectList(getMyBatisNameSpace("findAll"));
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