package servlet;

import constant.ContactSystemConstant;
import entity.Contact;
import entity.base.Result;
import exception.ContactNoExistException;
import exception.ContactUpdateNameExistException;
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
        Contact contactByService = null;
        try {
            contactByService = service.findContactById(contactId);
        } catch (ContactNoExistException e) {
            e.printStackTrace();
            //要更新的联系人不存在
            Result result = ResponseUtil.createNoContentResult(false);
            result.setMsg("要更新的ContactId不存在");
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
        }
        if (contactByService != null) {
            //将请求传递过来的要求的信息覆盖掉表中现有的字段数据
            try {
                ParamsUtil.copyToBean(request, contactByService);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //更新数据
            boolean isSuccess;
            try {
                isSuccess = service.updateContact(contactByService);
            } catch (ContactUpdateNameExistException e) {
                e.printStackTrace();
                //要更新的联系人姓名已经存在了
                Result result = ResponseUtil.createNoContentResult(false);
                result.setMsg("要更改的新的联系人姓名已存在，请更换一个姓名再试");
                response.getWriter().write(ResponseUtil.convertResultToJson(result));
                return;
            }
            Result result = ResponseUtil.createNoContentResult(isSuccess);
            response.getWriter().write(ResponseUtil.convertResultToJson(result));
        }
    }
}
