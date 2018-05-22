package service;

import entity.Contact;
import service.base.IService;

import java.util.ArrayList;

/**
 * Package: service
 * FileName: ContactService
 * Date: on 2018/5/21  下午2:53
 * Auther: Wally
 * Descirbe:联系人业务层Service接口
 */
public interface ContactService extends IService {
    /**
     * 添加联系人
     *
     * @param contact 联系人信息实体
     */
    boolean addContact(Contact contact);

    /**
     * 更新联系人
     *
     * @param contact 联系人信息实体
     */
    boolean updateContact(Contact contact);

    /**
     * 删除联系人
     *
     * @param contactId 要删除的联系人的id
     */
    boolean deleteContact(String contactId);

    /**
     * 查找联系人
     *
     * @return 要查找的联系人的id
     */
    Contact findContactById(String contactId);

    /**
     * 查找所有联系人
     */
    ArrayList<Contact> findAllContact();
}