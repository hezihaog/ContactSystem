package util;

import org.apache.commons.dbutils.QueryRunner;
import pool.C3p0Pool;
import pool.IPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Package: util
 * FileName: JdbcUtil
 * Date: on 2018/5/21  下午2:33
 * Auther: Wally
 * Descirbe:Jdbc工具类
 */
public class JdbcUtil {
    private static IPool sPool;

    private JdbcUtil() {
    }

    /**
     * 静态代码块中（只加载一次）
     */
    static {
        try {
            //配置连接池实现类
            sPool = new C3p0Pool();
            sPool.init();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("驱程程序注册出错");
        }
    }

    /**
     * 抽取获取连接对象的方法
     */
    public static Connection getConnection() {
        try {
            return sPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取查找对象，直接设置DataSource资源
     */
    public static QueryRunner getQueryRunner() {
        return new QueryRunner(sPool.getDataSource());
    }

    /**
     * 释放链接
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 释放资源的方法
     */
    public static void close(Connection conn, Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        close(conn);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new RuntimeException(e1);
            }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        close(conn);
    }
}