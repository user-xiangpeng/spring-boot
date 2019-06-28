package com.springboot.xp.controller;

import com.springboot.xp.bean.AuthSession;
import com.springboot.xp.bean.Result;
import com.springboot.xp.dao.mysql.model.AdminAccount;
import com.springboot.xp.exception.BaseException;
import com.springboot.xp.service.IAdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
public class AccountController extends BaseController {

    @Autowired
    private IAdminAccountService adminAccountService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
        AdminAccount account = null;
        try {
            account = adminAccountService.login(email, password);
        } catch (BaseException e) {
            return Result.error(e.getErrorCode());
        }
        HttpSession session = request.getSession();
        //登录成功
        session.setAttribute(BaseController.AUTH_SESSION, new AuthSession(request, account));
        return Result.success(account);
    }

}
