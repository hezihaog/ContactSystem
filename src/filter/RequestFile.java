package filter;

import constant.CommonConstant;
import util.ParamsUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Package: filter
 * FileName: RequestFile
 * Date: on 2018/5/21  下午3:08
 * Auther: Wally
 * Descirbe:请求过滤器，给请求参数进行一些处理
 */
public class RequestFile implements Filter {
    /**
     * 过滤器初始化配置
     */
    private FilterConfig filterConfig;
    /**
     * 配置的编码字符集
     */
    private String mEncodingCharset;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.mEncodingCharset = this.filterConfig.getInitParameter(CommonConstant.Config.encoding);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletContext context = this.filterConfig.getServletContext();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //配置设置的字符集（get请求的处理已在Tomcat的server.xml中配置字符集为UTF-8，在这里不需要处理了，只处理post请求）
        request = ParamsUtil.configCharset(request, response, mEncodingCharset);
        //打印日志
        System.out.println("当前访问的项目为：" + context.getContextPath());
        System.out.println(RequestFile.class.getSimpleName() + "拦截到用户请求的地址：" + request.getServletPath());
        //将请求和响应继续下去
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}