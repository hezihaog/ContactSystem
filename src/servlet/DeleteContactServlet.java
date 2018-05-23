package servlet;

import constant.ContactSystemConstant;
import entity.base.Result;
import exception.ContactNoExistException;
import manager.ServiceManager;
import service.ContactService;
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
        String contactId = request.getParameter(ContactSystemConstant.ParamsKey.contactId);
        //缺少字段
        if (TextUtil.isEmpty(contactId)) {
            Result result = ResponseUtil.createNoContentResult(false);
            ResponseUtil.addLackParamsErrorMsg(result, ContactSystemConstant.ParamsKey.contactId);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
        } else {
            ContactService service = ServiceManager.getInstance().getContactService();
            Result result;
            boolean isSuccess = false;
            try {
                isSuccess = service.deleteContact(contactId);
                result = ResponseUtil.createNoContentResult(isSuccess);
            } catch (ContactNoExistException contactNoExistException) {
                contactNoExistException.printStackTrace();
                result = ResponseUtil.createNoContentResult(false);
                result.setMsg("删除失败，contactId不存在");
                response.getWriter().write(ResponseUtil.convertResultToJson(result));
                return;
            }
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
        }
    }
}