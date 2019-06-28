package com.springboot.xp.service;

import com.springboot.xp.dao.mysql.model.AdminAccount;
import com.springboot.xp.exception.BaseException;

import java.util.List;

public interface IAdminAccountService {

    List<AdminAccount> all();

    AdminAccount getById(String id);

    AdminAccount login(String email, String password) throws BaseException;
}
