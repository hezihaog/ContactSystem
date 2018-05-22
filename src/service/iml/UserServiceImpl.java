package service.iml;

import dao.UserDao;
import dao.iml.UserDaoByMySQLImpl;
import entity.User;
import exception.UserExistException;
import service.UserService;

/**
 * Package: service.iml
 * FileName: UserServiceImpl
 * Date: on 2018/5/22  下午5:28
 * Auther: Wally
 * Descirbe:
 */
public class UserServiceImpl implements UserService {
    private final UserDao mDao;

    public UserServiceImpl() {
        mDao = new UserDaoByMySQLImpl();
    }

    @Override
    public void register(User user) throws UserExistException {
        boolean isExist = mDao.checkUserIsExist(user);
        if (isExist) {
            throw new UserExistException();
        } else {
            //第一次注册，保存用户信息
            saveUser(user);
        }
    }

    @Override
    public void saveUser(User user) {
        mDao.addUser(user);
    }
}
