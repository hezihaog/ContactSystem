package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Package: util
 * FileName: MybatisHelper
 * Date: on 2018/6/1  下午4:50
 * Auther: Wally
 * Descirbe:
 */
public class MybatisHelper {
    private volatile static MybatisHelper instance;
    private ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
    private SqlSessionFactory sqlSessionFactory;

    private MybatisHelper() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static MybatisHelper getInstance() {
        if (instance == null) {
            synchronized (MybatisHelper.class) {
                if (instance == null) {
                    instance = new MybatisHelper();
                }
            }
        }
        return instance;
    }

    /**
     * 获取SqlSession
     */
    public SqlSession getSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭SqlSession，并与当前线程分离
     */
    public void closeSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession != null) {
            sqlSession.close();
            threadLocal.remove();
        }
    }
}