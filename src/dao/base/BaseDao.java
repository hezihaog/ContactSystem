package dao.base;

import constant.CommonConstant;
import entity.IPageRequestParams;
import entity.base.IContent;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Package: dao.base
 * FileName: BaseDao
 * Date: on 2018/5/23  上午8:37
 * Auther: Wally
 * Descirbe:Dao层基类
 */
public abstract class BaseDao<T extends IContent> implements IDao<T> {
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
    protected <T> ArrayList<T> query(String sql, Object[] paramsValue, Class<T> clazz) {
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
    protected <T> T querySingle(String sql, Object[] paramsValue, Class<T> clazz) {
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

    @Override
    public int getTotalCount() {
        String sql = "SELECT count(*) FROM contact";
        QueryRunner runner = JdbcUtil.getQueryRunner();
        try {
            Long count = runner.query(sql, new ScalarHandler<Long>());
            return count.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public final List<T> findAllWithPage(IPageRequestParams pageParams) {
        //获取总条数
        int totalCount = getTotalCount();
        //越界处理
        if (pageParams.getCurrentPage() <= 0) {
            pageParams.setCurrentPage(CommonConstant.Page.DEFAULT_START_PAGE_INDEX);
        } else if (pageParams.getCurrentPage() > calculateTotalPage(totalCount, pageParams.getPageCount())) {
            pageParams.setCurrentPage(totalCount);
        }
        //计算起始行角标
        int currentPage = pageParams.getCurrentPage();
        int startIndex = (currentPage - 1) * pageParams.getPageCount();
        int count = pageParams.getPageCount();
        return onFindAllWithPage(new PageMsg(startIndex, count));
    }

    /**
     * 计算总页数
     *
     * @param totalCount 数据总条数
     * @param pageCount  总页数
     * @return 分页后的总页数
     */
    protected int calculateTotalPage(int totalCount, int pageCount) {
        //如果刚好能够整除平分，就直接返回整除
        if (totalCount % pageCount == 0) {
            return totalCount / pageCount;
        } else {
            //除了能够填满的那几页，剩下的不能填满一页，就加上一页来显示
            return (totalCount / pageCount) + 1;
        }
    }

    /**
     * 将处理过的信息给子类复写
     *
     * @param pageMsg 分页信息
     */
    protected abstract List<T> onFindAllWithPage(PageMsg pageMsg);
}