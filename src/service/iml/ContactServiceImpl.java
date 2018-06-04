package service.iml;

import constant.ContactSystemConstant;
import dao.ContactDao;
import dao.iml.ContactDaoByMyBatisImpl;
import entity.Contact;
import entity.IPageRequestParams;
import exception.ContactExistException;
import exception.ContactNoExistException;
import exception.ContactUpdateNameExistException;
import service.ContactService;

import java.util.HashMap;
import java.util.List;

/**
 * Package: service.iml
 * FileName: ContactServiceImpl
 * Date: on 2018/5/21  下午2:55
 * Auther: Wally
 * Descirbe:联系人业务层Service实现类
 */
public class ContactServiceImpl implements ContactService {
    private final ContactDao mDao;

    public ContactServiceImpl() {
        mDao = new ContactDaoByMyBatisImpl();
    }

    @Override
    public boolean add(Contact contact) throws ContactExistException {
        //先检查是否已经有存在同名的联系人
        boolean isExist = mDao.checkIsExist(contact);
        if (isExist) {
            //已经存在，抛出异常，通知调用方
            throw new ContactExistException();
        } else {
            //第一次添加，进行添加
            return mDao.add(contact);
        }
    }

    @Override
    public boolean update(Contact contact) throws ContactUpdateNameExistException {
        Contact contactByName;
        try {
            contactByName = findByName(contact.getUserId(), contact.getName());
        } catch (ContactNoExistException e) {
            //要更新的名字不存在表明是正常的，可以执行更新
            return mDao.update(contact);
        }
        if (contactByName != null) {
            //要更新的名字存在，抛出异常
            throw new ContactUpdateNameExistException();
        } else {
            return mDao.update(contact);
        }
    }

    @Override
    public boolean delete(String userId, String contactId) throws ContactNoExistException {
        Contact contact = mDao.findById(userId, contactId);
        if (contact == null) {
            throw new ContactNoExistException();
        }
        return mDao.delete(userId, contactId);
    }

    @Override
    public boolean deleteList(String userId, String[] ids) {
        return mDao.deleteList(userId, ids);
    }

    @Override
    public Contact findById(String userId, String contactId) throws ContactNoExistException {
        return mDao.findById(userId, contactId);
    }

    @Override
    public Contact findByName(String userId, String contactName) throws ContactNoExistException {
        return mDao.findByName(userId, contactName);
    }

    @Override
    public List<Contact> findAll(String userId) {
        return mDao.findAll();
    }

    @Override
    public List<Contact> findAllWithPage(String userId, IPageRequestParams pageParams) {
        HashMap<String, Object> args = new HashMap<String, Object>();
        args.put(ContactSystemConstant.DataKey.KEY_USER_ID, userId);
        return mDao.findAllWithPage(args, pageParams);
    }
}