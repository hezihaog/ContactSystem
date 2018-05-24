package entity;

/**
 * Package: entity
 * FileName: IPageRequestParams
 * Date: on 2018/5/24  下午5:23
 * Auther: Wally
 * Descirbe:分页请求参数封装接口
 */
public interface IPageRequestParams {
    /**
     * 设置当前页
     */
    void setCurrentPage(int currentPage);

    /**
     * 获取当前页
     */
    int getCurrentPage();

    /**
     * 设置每页显示的行数
     */
    void setPageCount(int pageCount);

    /**
     * 获取每页显示的行数
     */
    int getPageCount();
}