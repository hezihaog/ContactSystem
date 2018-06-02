package service;

import entity.Contact;
import entity.IPageRequestParams;
import exception.ContactExistException;
import exception.ContactNoExistException;
import exception.ContactUpdateNameExistException;
import service.base.IService;

import java.util.List;

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
    boolean add(Contact contact) throws ContactExistException;

    /**
     * 更新联系人
     *
     * @param contact 联系人信息实体
     */
    boolean update(Contact contact) throws ContactUpdateNameExistException;

    /**
     * 删除联系人
     *
     * @param contactId 要删除的联系人的id
     */
    boolean delete(String contactId) throws ContactNoExistException;

    /**
     * 删除多个id的联系人
     *
     * @param ids 联系人id数组
     */
    boolean deleteList(String[] ids);

    /**
     * 根据Id查找联系人
     *
     * @return 要查找的联系人的id
     */
    Contact findById(String contactId) throws ContactNoExistException;

    /**
     * 根据联系人姓名查找联系人
     *
     * @param contactName 联系人姓名
     */
    Contact findByName(String contactName) throws ContactNoExistException;

    /**
     * 查找所有联系人
     */
    List<Contact> findAll();

    /**
     * 查询所有联系人并带分页
     *
     * @param pageParams 分页的请求参数
     */
    List<Contact> findAllWithPage(IPageRequestParams pageParams);
}