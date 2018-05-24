package pool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Package: pool
 * FileName: IPool
 * Date: on 2018/5/24  下午4:08
 * Auther: Wally
 * Descirbe:连接池接口
 */
public interface IPool {
    /**
     * 初始化
     */
    void init() throws Exception;

    /**
     * 获取连接
     */
    Connection getConnection() throws SQLException;

    /**
     * 获取数据集
     */
    DataSource getDataSource();
}