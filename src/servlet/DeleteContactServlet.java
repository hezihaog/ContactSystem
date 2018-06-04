package servlet;

import constant.ContactSystemConstant;
import entity.base.Result;
import exception.ContactNoExistException;
import manager.ServiceManager;
import service.ContactService;
import util.ParamsUtil;
import util.ResponseUtil;
import util.TextUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Package: ${PACKAGE_NAME}
 * FileName: ${NAME}
 * Date: on 2018/5/21  下午2:58
 * Auther: Wally
 * Descirbe:
 */
public class DeleteContactServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contactId = ParamsUtil.getParams(request, ContactSystemConstant.ParamsKey.contactId);
        String userId = ParamsUtil.getParams(request, ContactSystemConstant.ParamsKey.userId);
        //缺少字段
        if (TextUtil.isEmpty(contactId)) {
            ResponseUtil.writeLackParamsErrorMsg(response, ContactSystemConstant.ParamsKey.contactId);
            return;
        }
        if (TextUtil.isEmpty(userId)) {
            ResponseUtil.writeLackParamsErrorMsg(response, ContactSystemConstant.ParamsKey.userId);
            return;
        }
        //多个id拼接删除
        String[] ids = contactId.split(",");
        Result result;
        if (ids.length >= 2) {
            ContactService service = ServiceManager.getInstance().getContactService();
            boolean isSuccess = service.deleteList(userId, ids);
            if (isSuccess) {
                response.getWriter().write(ResponseUtil.convertResultToJson(ResponseUtil.createNoContentResult(true)));
            } else {
                response.getWriter().write(ResponseUtil.convertResultToJson(ResponseUtil.createNoContentResult(false)));
            }
        } else if (ids.length == 1) {
            //删除单个联系人
            ContactService service = ServiceManager.getInstance().getContactService();
            try {
                boolean isSuccess = service.delete(userId, contactId);
                result = ResponseUtil.createNoContentResult(isSuccess);
                response.getWriter().write(ResponseUtil.convertResultToJson(result));
            } catch (ContactNoExistException contactNoExistException) {
                contactNoExistException.printStackTrace();
                result = ResponseUtil.createNoContentResult(false);
                result.setMsg("删除失败，contactId不存在");
                response.getWriter().write(ResponseUtil.convertResultToJson(result));
            }
        } else {
            //异常情况，传了非法字符
            response.getWriter().write(ResponseUtil.convertResultToJson(ResponseUtil.createNoContentResult(false)));
        }
    }
}