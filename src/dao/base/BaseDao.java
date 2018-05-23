package dao.base;

import org.apache.commons.beanutils.BeanUtils;
import util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;

/**
 * Package: dao.base
 * FileName: BaseDao
 * Date: on 2018/5/23  上午8:37
 * Auther: Wally
 * Descirbe:Dao层基类
 */
public class BaseDao {
    /**
     * 统一更新方法（update、insert、delete）
     *
     * @param sql         要执行的sql
     * @param paramsValue sql中参数的占位符对应的值
     */
    protected boolean update(String sql, Object[] paramsValue) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement(sql);
            int count = statement.getParameterMetaData().getParameterCount();
            if (paramsValue != null && count > 0) {
                for (int i = 0; i < paramsValue.length; i++) {
                    statement.setObject(i + 1, paramsValue[i]);
                }
            }
            int affectedNum = statement.executeUpdate();
            return affectedNum > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement);
        }
    }

    /**
     * 通用查询方法
     *
     * @param sql         要执行的sql
     * @param paramsValue sql中占位符中的值
     * @param clazz       包装表中的数据的Bean类的Class
     */
    protected  <T> ArrayList<T> query(String sql, Object[] paramsValue, Class<T> clazz) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<T> list = new ArrayList<T>();
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement(sql);
            ParameterMetaData metaData = statement.getParameterMetaData();
            //获取占位符的个数
            int count = metaData.getParameterCount();
            if (paramsValue != null && paramsValue.length > 0) {
                for (int i = 0; i < count; i++) {
                    statement.setObject(i + 1, paramsValue[0]);
                }
            }
            resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //获取结果集中列的个数
            int columnCount = resultSetMetaData.getColumnCount();
            //遍历封装数据
            while (resultSet.next()) {
                T bean = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = resultSetMetaData.getColumnName(i + 1);
                    Object value = resultSet.getObject(columnName);
                    BeanUtils.copyProperty(bean, columnName, value);
                }
                list.add(bean);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement, resultSet);
        }
        return list;
    }

    /**
     * 查询单个
     *
     * @param sql         要执行的sql
     * @param paramsValue sql中占位符中的值
     * @param clazz       包装表中的数据的Bean类的Class
     */
    protected  <T> T querySingle(String sql, Object[] paramsValue, Class<T> clazz) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement(sql);
            ParameterMetaData metaData = statement.getParameterMetaData();
            //获取占位符的个数
            int count = metaData.getParameterCount();
            if (paramsValue != null && paramsValue.length > 0) {
                for (int i = 0; i < count; i++) {
                    statement.setObject(i + 1, paramsValue[0]);
                }
            }
            resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //获取结果集中列的个数
            int columnCount = resultSetMetaData.getColumnCount();
            //遍历封装数据
            if (resultSet.next()) {
                T bean = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = resultSetMetaData.getColumnName(i + 1);
                    Object value = resultSet.getObject(columnName);
                    BeanUtils.copyProperty(bean, columnName, value);
                }
                return bean;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    /**
     * 检查是否存在
     *
     * @param sql         要执行的Sql
     * @param paramsValue 参数数组
     */
    protected boolean checkIsExist(String sql, Object[] paramsValue) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.prepareStatement(sql);
            ParameterMetaData metaData = statement.getParameterMetaData();
            //获取占位符的个数
            int count = metaData.getParameterCount();
            if (paramsValue != null && paramsValue.length > 0) {
                for (int i = 0; i < count; i++) {
                    statement.setObject(i + 1, paramsValue[0]);
                }
            }
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, statement, resultSet);
        }
        return false;
    }
}