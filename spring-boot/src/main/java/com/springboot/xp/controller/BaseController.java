package com.springboot.xp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.springboot.xp.bean.AuthSession;
import com.springboot.xp.exception.BaseException;

public class BaseController {
	
	public static final String AUTH_SESSION = "authSession";
	
	protected String checkLogin(HttpServletRequest request) throws BaseException {
		HttpSession session = request.getSession();
		AuthSession authSession =(AuthSession) session.getAttribute(AUTH_SESSION);
		if (null == authSession) {
			throw new BaseException(BaseException.NO_LOGIN);
		}
		return authSession.getUserId();
	}

}
