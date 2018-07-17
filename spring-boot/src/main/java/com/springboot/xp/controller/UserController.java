package com.springboot.xp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.xp.bean.Result;
import com.springboot.xp.dao.model.AdminAccount;
import com.springboot.xp.exception.BaseException;
import com.springboot.xp.service.IAdminAccountService;

@RestController
public class UserController extends BaseController {
	
	@Autowired
	private IAdminAccountService adminAccountService;

	@GetMapping("/hello")
	public String login() {
		return "hi, i'm springboot !";
	}
	
	@GetMapping("/user/all")
	public Result allUser() {
		List<AdminAccount> all = adminAccountService.all();
		if (CollectionUtils.isEmpty(all)) {
			return Result.success();
		}else{
			return Result.success(all);
		}
	}
	
	@GetMapping("/user/info")
	public Result getUserInfo(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id) throws BaseException {
		checkLogin(request);
		return Result.success(adminAccountService.getById(id));
	}
	
	
}
