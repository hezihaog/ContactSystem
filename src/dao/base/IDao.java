package dao.base;

import entity.IPageRequestParams;
import entity.base.IContent;

import java.util.List;
import java.util.Map;

/**
 * Package: dao.base
 * FileName: IDao
 * Date: on 2018/5/24  下午5:07
 * Auther: Wally
 * Descirbe:Dao层基础接口
 */
public interface IDao<T extends IContent> {
    /**
     * 获取全部页的总条数
     */
    int getTotalCount();

    /**
     * 查询所有，不带分页
     */
    List<T> findAll();

    /**
     * 查询所有，传递需要的参数
     *
     * @param args 装载参数的Map容器
     */
    List<T> findAll(Map<String, Object> args);

    /**
     * 查询所有，并带分页
     *
     * @param pageParams 分页请求参数
     */
    List<T> findAllWithPage(IPageRequestParams pageParams);

    /**
     * 查询所有，并带分页
     *
     * @param args       args 装载参数的Map容器
     * @param pageParams 分页请求参数
     */
    List<T> findAllWithPage(Map<String, Object> args, IPageRequestParams pageParams);
}