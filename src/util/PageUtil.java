package util;

import constant.CommonConstant;
import entity.IPageRequestParams;
import entity.PageRequestParams;

import javax.servlet.http.HttpServletRequest;

/**
 * Package: util
 * FileName: PageUtil
 * Date: on 2018/5/24  下午7:30
 * Auther: Wally
 * Descirbe:分页帮助类
 */
public class PageUtil {
    private PageUtil() {
    }

    /**
     * 解析分页的请求参数
     *
     * @param request 请求对象
     */
    public static IPageRequestParams parsePageRequestParams(HttpServletRequest request) {
        String page = request.getParameter(CommonConstant.Key.page);
        String size = request.getParameter(CommonConstant.Key.size);
        //如果没有传，则使用默认的
        if (TextUtil.isEmpty(page)) {
            page = String.valueOf(CommonConstant.Page.DEFAULT_START_PAGE_INDEX);
        }
        if (TextUtil.isEmpty(size)) {
            size = String.valueOf(CommonConstant.Page.DEFAULT_PAGE_COUNT);
        }
        return new PageRequestParams(Integer.valueOf(page), Integer.valueOf(size));
    }
}