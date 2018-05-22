package servlet;

import constant.ContactSystemConstant;
import entity.Contact;
import entity.base.Result;
import exception.ContactIsExistException;
import service.ContactService;
import service.iml.ContactServiceImpl;
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
        //获取请求过来的参数
        String name = request.getParameter(ContactSystemConstant.ParamsKey.name);
        String gender = request.getParameter(ContactSystemConstant.ParamsKey.gender);
        String age = request.getParameter(ContactSystemConstant.ParamsKey.age);
        String phone = request.getParameter(ContactSystemConstant.ParamsKey.phone);
        String email = request.getParameter(ContactSystemConstant.ParamsKey.email);
        String qq = request.getParameter(ContactSystemConstant.ParamsKey.qq);
        //每个参数都是必传
        if (TextUtil.isEmpty(name)) {
            Result result = ResponseUtil.createNoContentResult(false);
            ResponseUtil.addLackParamsErrorMsg(result, ContactSystemConstant.ParamsKey.name);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
            return;
        }
        if (TextUtil.isEmpty(gender)) {
            Result result = ResponseUtil.createNoContentResult(false);
            ResponseUtil.addLackParamsErrorMsg(result, ContactSystemConstant.ParamsKey.gender);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
            return;
        }
        if (TextUtil.isEmpty(age)) {
            Result result = ResponseUtil.createNoContentResult(false);
            ResponseUtil.addLackParamsErrorMsg(result, ContactSystemConstant.ParamsKey.age);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
            return;
        }
        if (TextUtil.isEmpty(phone)) {
            Result result = ResponseUtil.createNoContentResult(false);
            ResponseUtil.addLackParamsErrorMsg(result, ContactSystemConstant.ParamsKey.phone);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
            return;
        }
        if (TextUtil.isEmpty(email)) {
            Result result = ResponseUtil.createNoContentResult(false);
            ResponseUtil.addLackParamsErrorMsg(result, ContactSystemConstant.ParamsKey.email);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
            return;
        }
        if (TextUtil.isEmpty(qq)) {
            Result result = ResponseUtil.createNoContentResult(false);
            ResponseUtil.addLackParamsErrorMsg(result, ContactSystemConstant.ParamsKey.qq);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
            return;
        }
        //将数据封装到entity
        Contact contact = new Contact();
        contact.setName(name);
        contact.setGender(gender);
        try {
            contact.setAge(Integer.valueOf(age));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        contact.setPhone(phone);
        contact.setEmail(email);
        contact.setQq(qq);
        //调用业务层进行添加联系人
        ContactService service = new ContactServiceImpl();
        Result result = ResponseUtil.createNoContentResult();
        try {
            service.addContact(contact);
            result.setSuccess();
        } catch (ContactIsExistException e) {
            e.printStackTrace();
            //联系人已经存在
            result.setError();
            result.setMsg("联系人已存在");
        }
        response.getWriter().write(ResponseUtil.convertResultToJson(result));
    }
}