package servlet;

import constant.UserConstant;
import entity.User;
import entity.base.Result;
import exception.UserExistException;
import manager.ServiceManager;
import service.UserService;
import util.MD5Util;
import util.ResponseUtil;
import util.TextUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Package: servlet
 * FileName: RegisterUserServlet
 * Date: on 2018/5/22  下午5:32
 * Auther: Wally
 * Descirbe:
 */
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter(UserConstant.ParamsKey.userName);
        String pwd = req.getParameter(UserConstant.ParamsKey.pwd);
        Result result = null;
        if (TextUtil.isEmpty(userName)) {
            result = ResponseUtil.createNoContentResult(false);
            ResponseUtil.addLackParamsErrorMsg(result, UserConstant.ParamsKey.userName);
        }
        if (TextUtil.isEmpty(pwd)) {
            result = ResponseUtil.createNoContentResult(false);
            ResponseUtil.addLackParamsErrorMsg(result, UserConstant.ParamsKey.pwd);
        }
        //参数不全
        if (result != null) {
            resp.getWriter().write(ResponseUtil.convertResultToJson(result));
            return;
        }
        User user = new User();
        user.setUserName(userName);
        //密码需要md5加密后存入数据库
        user.setPwd(MD5Util.MD5(pwd));
        UserService userService = ServiceManager.getInstance().getUserService();
        try {
            userService.register(user);
        } catch (UserExistException e) {
            e.printStackTrace();
            result = ResponseUtil.createNoContentResult(false);
            result.setMsg("注册的用户已存在，请更换后再试");
            resp.getWriter().write(ResponseUtil.convertResultToJson(result));
            return;
        }
        result = ResponseUtil.createNoContentResult();
        resp.getWriter().write(ResponseUtil.convertResultToJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}