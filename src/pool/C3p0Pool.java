package pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Package: pool
 * FileName: C3p0Pool
 * Date: on 2018/5/24  下午4:07
 * Auther: Wally
 * Descirbe:C3P0连接池
 */
public class C3p0Pool implements IPool {
    private ComboPooledDataSource mDataSource;

    @Override
    public void init() throws Exception {
        mDataSource = new ComboPooledDataSource();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return mDataSource.getConnection();
    }

    @Override
    public DataSource getDataSource() {
        return mDataSource;
    }
}