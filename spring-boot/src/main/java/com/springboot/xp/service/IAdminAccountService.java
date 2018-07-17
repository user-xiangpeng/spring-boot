package com.springboot.xp.service;

import java.util.List;

import com.springboot.xp.dao.model.AdminAccount;
import com.springboot.xp.exception.BaseException;

public interface IAdminAccountService {

	List<AdminAccount> all();
	
	AdminAccount getById(String id);

	AdminAccount login(String email, String password) throws BaseException;
}
