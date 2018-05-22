package service.iml;

import dao.ContactDao;
import dao.iml.ContactDaoByMySQLImpl;
import entity.Contact;
import service.ContactService;

import java.util.ArrayList;

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
        mDao = new ContactDaoByMySQLImpl();
    }

    @Override
    public boolean addContact(Contact contact) {
        return mDao.addContact(contact);
    }

    @Override
    public boolean updateContact(Contact contact) {
        return mDao.updateContact(contact);
    }

    @Override
    public boolean deleteContact(String contactId) {
        return mDao.deleteContact(contactId);
    }

    @Override
    public Contact findContactById(String contactId) {
        return mDao.findContactById(contactId);
    }

    @Override
    public ArrayList<Contact> findAllContact() {
        return mDao.findAllContact();
    }
}