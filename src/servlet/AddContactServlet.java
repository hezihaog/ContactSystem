package servlet;

import constant.ContactSystemConstant;
import entity.Contact;
import entity.base.Result;
import exception.ContactExistException;
import service.ContactService;
import service.iml.ContactServiceImpl;
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
 * Date: on 2018/5/21  下午2:56
 * Auther: Wally
 * Descirbe:
 */
public class AddContactServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Contact contact = new Contact();
        try {
            contact = ParamsUtil.copyToBean(request, contact);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //姓名必传
        if (TextUtil.isEmpty(contact.getName())) {
            Result result = ResponseUtil.createNoContentResult(false);
            ResponseUtil.addLackParamsErrorMsg(result, ContactSystemConstant.ParamsKey.name);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
            return;
        }
        //调用业务层进行添加联系人
        ContactService service = new ContactServiceImpl();
        Result result = ResponseUtil.createNoContentResult();
        try {
            service.add(contact);
            result.setSuccess();
        } catch (ContactExistException e) {
            e.printStackTrace();
            //联系人已经存在
            result.setError();
            result.setMsg("联系人已存在");
        }
        response.getWriter().write(ResponseUtil.convertResultToJson(result));
    }
}