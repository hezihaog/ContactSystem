package servlet;

import constant.ContactSystemConstant;
import entity.Contact;
import entity.base.Result;
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
public class UpdateContactServlet extends HttpServlet {
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
            return;
        }
        ContactService service = ServiceManager.getInstance().getContactService();
        Contact contactByService = service.findContactById(contactId);
        if (contactByService != null) {
            //获取请求过来的参数
            String name = request.getParameter(ContactSystemConstant.ParamsKey.name);
            String gender = request.getParameter(ContactSystemConstant.ParamsKey.gender);
            int age = Integer.valueOf(request.getParameter(ContactSystemConstant.ParamsKey.age));
            String phone = request.getParameter(ContactSystemConstant.ParamsKey.phone);
            String email = request.getParameter(ContactSystemConstant.ParamsKey.email);
            String qq = request.getParameter(ContactSystemConstant.ParamsKey.qq);
            //更新数据
            contactByService.setName(name);
            contactByService.setGender(gender);
            contactByService.setAge(age);
            contactByService.setPhone(phone);
            contactByService.setEmail(email);
            contactByService.setQq(qq);
            boolean isSuccess = service.updateContact(contactByService);
            Result result = ResponseUtil.createNoContentResult(isSuccess);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
        }
    }
}
