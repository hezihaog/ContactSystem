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
    boolean add(Contact contact);

    /**
     * 更新联系人
     *
     * @param contact 联系人信息实体
     */
    boolean update(Contact contact);

    /**
     * 删除联系人
     *
     * @param userId    用户id
     * @param contactId 要删除的联系人的id
     */
    boolean delete(String userId, String contactId);

    /**
     * 删除多个id的联系人
     *
     * @param userId 用户id
     * @param ids    联系人id数组
     */
    boolean deleteList(String userId, String[] ids);

    /**
     * 根据id查找联系人
     *
     * @param userId 用户id
     * @return 要查找的联系人的id
     */
    Contact findById(String userId, String contactId) throws ContactNoExistException;

    /**
     * 根据联系人姓名查找联系人
     *
     * @param contactName 联系人姓名
     */
    Contact findByName(String userId, String contactName) throws ContactNoExistException;

    /**
     * 检查联系人是否存在
     *
     * @param contact 需要检查的联系人实体
     */
    boolean checkIsExist(Contact contact);
}