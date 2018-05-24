package entity;

import constant.CommonConstant;

/**
 * Package: entity
 * FileName: PageRequestParams
 * Date: on 2018/5/24  下午5:22
 * Auther: Wally
 * Descirbe:分页请求参数封装接口实现
 */
public class PageRequestParams implements IPageRequestParams {
    //当前页
    private int currentPage = 1;
    //每页的条数数量
    private int pageCount = CommonConstant.Page.DEFAULT_PAGE_COUNT;

    public PageRequestParams(int currentPage, int pageCount) {
        this.currentPage = currentPage;
        this.pageCount = pageCount;
    }

    @Override
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public int getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public int getPageCount() {
        return this.pageCount;
    }
}
