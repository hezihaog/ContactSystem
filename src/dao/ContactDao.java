package dao;

import dao.base.IDao;
import entity.Contact;
import exception.ContactNoExistException;

/**
 * Package: dao
 * FileName: ContactDao
 * Date: on 2018/5/21  下午2:44
 * Auther: Wally
 * Descirbe:联系人Dao层基础接口
 */
public interface ContactDao extends IDao<Contact> {
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
     * @param id 要删除的联系人的id
     */
    boolean deleteContact(String id);

    /**
     * 根据id查找联系人
     *
     * @return 要查找的联系人的id
     */
    Contact findContactById(String id) throws ContactNoExistException;

    /**
     * 根据联系人姓名查找联系人
     *
     * @param contactName 联系人姓名
     */
    Contact findContactByName(String contactName) throws ContactNoExistException;

    /**
     * 检查联系人是否存在
     *
     * @param contact 需要检查的联系人实体
     */
    boolean checkContactIsExist(Contact contact);
}