package servlet;

import constant.ContactSystemConstant;
import entity.Contact;
import entity.base.Result;
import exception.ContactNoExistException;
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
 * Date: on 2018/5/21  下午3:00
 * Auther: Wally
 * Descirbe:
 */
public class ContactDetailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //根据传递过来的id查询联系人列表
        String contactId = request.getParameter(ContactSystemConstant.ParamsKey.contactId);
        //缺少字段
        if (TextUtil.isEmpty(contactId)) {
            Result result = ResponseUtil.createNoContentResult(false);
            ResponseUtil.addLackParamsErrorMsg(result, ContactSystemConstant.ParamsKey.contactId);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
        } else {
            ContactService service = new ContactServiceImpl();
            Contact contact;
            try {
                contact = service.findContactById(contactId);
            } catch (ContactNoExistException e) {
                e.printStackTrace();
                Result result = ResponseUtil.createNoContentResult(false);
                result.setMsg("要查看的联系人的contactId不存在！");
                response.getWriter().write(ResponseUtil.convertResultToJson(result));
                return;
            }
            Result result = ResponseUtil.createResultWithContent(contact);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
        }
    }
}