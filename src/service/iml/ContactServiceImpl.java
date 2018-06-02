package service.iml;

import dao.ContactDao;
import dao.iml.ContactDaoByMyBatisImpl;
import entity.Contact;
import entity.IPageRequestParams;
import exception.ContactExistException;
import exception.ContactNoExistException;
import exception.ContactUpdateNameExistException;
import service.ContactService;

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
    public boolean addContact(Contact contact) throws ContactExistException {
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
    public boolean updateContact(Contact contact) throws ContactUpdateNameExistException {
        Contact contactByName;
        try {
            contactByName = findContactByName(contact.getName());
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
    public boolean deleteContact(String contactId) throws ContactNoExistException {
        Contact contact = mDao.findById(contactId);
        if (contact == null) {
            throw new ContactNoExistException();
        }
        return mDao.delete(contactId);
    }

    @Override
    public Contact findContactById(String contactId) throws ContactNoExistException {
        return mDao.findById(contactId);
    }

    @Override
    public Contact findContactByName(String contactName) throws ContactNoExistException {
        return mDao.findByName(contactName);
    }

    @Override
    public List<Contact> findAllContact() {
        return mDao.findAll();
    }

    @Override
    public List<Contact> findAllContactWithPage(IPageRequestParams pageParams) {
        return mDao.findAllWithPage(pageParams);
    }
}