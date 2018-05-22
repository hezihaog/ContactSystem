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
    boolean addUser(User user);

    /**
     * 删除一个用户
     */
    boolean deleteUser(String userId);

    /**
     * 根据id查找一个用户
     */
    User findById(String userId);

    /**
     * 更新一个用户
     */
    boolean updateUser(User user);

    /**
     * 检查一个用户是否已经存在
     */
    boolean checkUserIsExist(User user);
}