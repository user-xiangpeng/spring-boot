package com.springboot.xp.service.impl;

import com.springboot.xp.dao.mysql.mapper.AdminAccountMapper;
import com.springboot.xp.dao.mysql.model.AdminAccount;
import com.springboot.xp.exception.BaseException;
import com.springboot.xp.service.IAdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminAccountServiceImpl implements IAdminAccountService {

    @Autowired
    private AdminAccountMapper adminAccountMapper;

    @Override
    public List<AdminAccount> all() {
        return adminAccountMapper.selectAll();
    }

    @Override
    public AdminAccount getById(String id) {
        return adminAccountMapper.selectByPrimaryKey(id);
    }

    @Override
    public AdminAccount login(String email, String password) throws BaseException {
        AdminAccount adminAccount = new AdminAccount();
        adminAccount.setEmpEmail(email);
        adminAccount.setEmpPwd(password);
        List<AdminAccount> list = adminAccountMapper.select(adminAccount);
        if (null == list) {
            throw new BaseException(BaseException.NOACCOUNT_OR_PWDERROR);
        }
        return adminAccount;
    }

}
