package servlet;

import constant.ContactSystemConstant;
import entity.Contact;
import entity.ContactList;
import entity.IPageRequestParams;
import entity.base.ArrayContent;
import entity.base.Result;
import service.ContactService;
import service.iml.ContactServiceImpl;
import util.PageUtil;
import util.ParamsUtil;
import util.ResponseUtil;
import util.TextUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Package: ${PACKAGE_NAME}
 * FileName: ${NAME}
 * Date: on 2018/5/21  下午2:59
 * Auther: Wally
 * Descirbe:
 */
public class AllContactServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户id
        String userId = ParamsUtil.getParams(request, ContactSystemConstant.ParamsKey.userId);
        if (TextUtil.isEmpty(userId)) {
            Result result = ResponseUtil.createNoContentResult(false);
            ResponseUtil.addLackParamsErrorMsg(result, ContactSystemConstant.ParamsKey.userId);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
            return;
        }
        IPageRequestParams pageRequestParams = PageUtil.parsePageRequestParams(request);
        //调用业务层的Service进行获取所有联系人
        ContactService service = new ContactServiceImpl();
        List<Contact> allContact = service.findAllWithPage(userId, pageRequestParams);
        ContactList contactList = new ContactList(new ArrayContent<Contact>(allContact));
        Result result = ResponseUtil.createResultWithContent(contactList);
        response.getWriter().write(ResponseUtil.convertResultToJson(result));
    }
}