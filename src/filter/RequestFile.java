package filter;
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
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletContext context = this.filterConfig.getServletContext();
        //初始化参数的字符集
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //解决post请求过来时的编码问题
        request.setCharacterEncoding("utf-8");
        //解决响应到浏览器的编码问题
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=utf-8");
        //打印日志
        System.out.println("当前访问的项目为：" + context.getContextPath());
        System.out.println(RequestFile.class.getSimpleName() + "拦截到用户请求的地址：" + request.getServletPath());
        //将请求和响应继续下去
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}