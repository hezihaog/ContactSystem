package manager;

import service.ContactService;
import service.iml.ContactServiceImpl;

/**
 * Package: manager
 * FileName: ServiceManager
 * Date: on 2018/5/21  下午4:01
 * Auther: Wally
 * Descirbe:服务管理器
 */
public class ServiceManager {
    private ServiceManager() {
    }

    private static final class SingleHolder {
        private static final ServiceManager instance = new ServiceManager();
    }

    /**
     * 获取实例
     */
    public static ServiceManager getInstance() {
        return SingleHolder.instance;
    }

    /**
     * 获取联系人业务层服务
     */
    public ContactService getContactService() {
        return new ContactServiceImpl();
    }
}