package pool;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Package: pool
 * FileName: DbcpPool
 * Date: on 2018/5/24  下午4:07
 * Auther: Wally
 * Descirbe:DBCP连接池
 */
public class DbcpPool implements IPool {
    private DataSource mDataSource;

    @Override
    public void init() throws Exception {
        //读取db.properties文件
        Properties props = new Properties();
        /**
         *  . 代表java命令运行的目录
         *  在java项目下，. java命令的运行目录从项目的根目录开始
         *  在web项目下，  . java命令的而运行目录从tomcat/bin目录开始
         *  所以不能使用点.
         */
        //FileInputStream in = new FileInputStream("./src/db.properties");

        /**
         * 使用类路径的读取方式
         *  / : 斜杠表示classpath的根目录
         *     在java项目下，classpath的根目录从bin目录开始
         *     在web项目下，classpath的根目录从WEB-INF/classes目录开始
         */
        InputStream in = DbcpPool.class.getResourceAsStream("/db.properties");
        //加载配置文件
        props.load(in);
        //连接池配置文件
        mDataSource = BasicDataSourceFactory.createDataSource(props);
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