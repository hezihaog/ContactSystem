package dao;

import entity.User;

/**
 * Package: dao
 * FileName: UserDao
 * Date: on 2018/5/22  下午5:03
 * Auther: Wally
 * Descirbe:用户Dao接口
 */
public interface UserDao {
    /**
     * 增加一个用户
     */
    boolean add(User user);

    /**
     * 删除一个用户
     */
    boolean delete(String userId);

    /**
     * 根据id查找一个用户
     */
    User findById(String userId);

    /**
     * 更新一个用户
     */
    boolean update(User user);

    /**
     * 检查一个用户是否已经存在
     */
    boolean checkIsExist(User user);
}