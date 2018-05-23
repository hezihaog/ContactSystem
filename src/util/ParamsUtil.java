package util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 请求参数工具类
 */
public class ParamsUtil {
    /**
     * 解决get请求过来时的编码问题
     *
     * @param request   请求对象
     * @param paramsKey 请求参数的Key
     * @return 正常使用utf-8解码的参数值
     */
    public static String getParams(HttpServletRequest request, String paramsKey) {
        String paramsValue;
        try {
            paramsValue = request.getParameter(paramsKey);
            paramsValue = URLEncoder.encode(paramsValue, "ISO-8859-1");
            paramsValue = URLDecoder.decode(paramsValue, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return paramsValue;
    }

    /**
     * 转换请求字段到Bean
     *
     * @param request 请求
     * @param clazz   转换实体bean的class
     * @param <T>     转换出来的bean
     */
    public static <T> T copyToBean(HttpServletRequest request, Class<T> clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        T bean = clazz.newInstance();
        return copyToBean(request, bean);
    }

    /**
     * 将请求字段覆盖到Bean
     *
     * @param request    请求
     * @param originBean 已经存在的实体bean独享
     * @param <T>        覆盖属性的实体bean
     */
    public static <T> T copyToBean(HttpServletRequest request, T originBean) throws InvocationTargetException, IllegalAccessException {
        Map parameterMap = request.getParameterMap();
        BeanUtils.populate(originBean, parameterMap);
        return originBean;
    }
}