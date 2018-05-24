package service;

import entity.User;
import exception.UserExistException;
import service.base.IService;

/**
 * Package: service
 * FileName: UserService
 * Date: on 2018/5/22  下午5:24
 * Auther: Wally
 * Descirbe:User业务层
 */
public interface UserService extends IService {
    /**
     * 注册一个用户
     */
    boolean register(User user) throws UserExistException;

    /**
     * 保存一个用户信息
     */
    boolean saveUser(User user);
}