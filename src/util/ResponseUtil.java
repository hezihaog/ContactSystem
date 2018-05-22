package util;

import com.alibaba.fastjson.JSON;
import entity.base.IContent;
import entity.base.Result;

/**
 * Package: util
 * FileName: ResponseUtil
 * Date: on 2018/5/21  下午10:17
 * Auther: Wally
 * Descirbe:
 */
public class ResponseUtil {
    private ResponseUtil() {
    }

    /**
     * 设置结果的状态
     *
     * @param result    结果实体
     * @param isSuccess 是否成功
     */
    public static void setResultStatus(Result result, boolean isSuccess) {
        if (isSuccess) {
            result.setSuccess();
        } else {
            result.setError();
        }
    }

    /**
     * 根据传入的content创建一个带Content的Result，默认是成功的
     *
     * @param content 内容
     */
    public static Result createResultWithContent(IContent content) {
        return createResultWithContent(content, true);
    }

    /**
     * 根据传入的content创建一个带Content的Result，可指定是否成功
     *
     * @param content   内容
     * @param isSuccess 是否成功
     */
    public static Result createResultWithContent(IContent content, boolean isSuccess) {
        Result result = new Result();
        result.setContent(content);
        setResultStatus(result, isSuccess);
        return result;
    }

    /**
     * 创建一个没有content内容的结果，默认是成功状态
     */
    public static Result createNoContentResult() {
        return createNoContentResult(true);
    }

    /**
     * 创建一个没有content内容的结果
     *
     * @param isSuccess 是否成功
     */
    public static Result createNoContentResult(boolean isSuccess) {
        Result result = new Result();
        setResultStatus(result, isSuccess);
        return result;
    }

    /**
     * 转换Result为Json
     *
     * @param result 响应结果
     */
    public static String convertResultToJson(Result result) {
        return JSON.toJSONString(result);
    }

    /**
     * 添加缺少字段到结果
     *
     * @param result         结果实体
     * @param lackParamsName 多个缺少的参数名
     */
    public static void addLackParamsErrorMsg(Result result, String... lackParamsName) {
        StringBuffer buffer = new StringBuffer("lack field ");
        for (String paramsName : lackParamsName) {
            buffer.append(paramsName);
            buffer.append("，");
        }
        String msg = buffer.toString();
        msg = msg.substring(0, msg.length() - 1);
        result.addMsg(msg);
    }
}