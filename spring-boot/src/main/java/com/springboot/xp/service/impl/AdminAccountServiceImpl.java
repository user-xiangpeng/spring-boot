package com.springboot.xp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.xp.dao.mysql.mapper.AdminAccountMapper;
import com.springboot.xp.dao.mysql.model.AdminAccount;
import com.springboot.xp.dao.mysql.model.AdminAccountExample;
import com.springboot.xp.exception.BaseException;
import com.springboot.xp.service.IAdminAccountService;

@Service
public class AdminAccountServiceImpl implements IAdminAccountService {

    @Autowired
    private AdminAccountMapper adminAccountMapper;

    @Override
    public List<AdminAccount> all() {
        return adminAccountMapper.selectByExample(new AdminAccountExample());
    }

    @Override
    public AdminAccount getById(String id) {
        return adminAccountMapper.selectByPrimaryKey(id);
    }

    @Override
    public AdminAccount login(String email, String password) throws BaseException {
        AdminAccountExample example = new AdminAccountExample();
        example.createCriteria().andEmpEmailEqualTo(email).andEmpPwdEqualTo(password);
        List<AdminAccount> list = adminAccountMapper.selectByExample(example);
        if (null == list) {
            throw new BaseException(BaseException.NOACCOUNT_OR_PWDERROR);
        }
        return list.get(0);
    }

}
