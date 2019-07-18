package com.springboot.xp.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.xp.dao.mysql.mapper.ExtraGwAccountMapper;
import com.springboot.xp.dao.mysql.model.GwAccount;
import com.springboot.xp.service.IGwAccountService;

@Service
public class GwAccountServiceImpl implements IGwAccountService {

    @Autowired
    private ExtraGwAccountMapper extraGwAccountMapper;

    @Override
    public List<GwAccount> listByLimit(int startRow, int maxRows) {
        if (startRow < 0 || maxRows <= 0) {
            return Collections.emptyList();
        }
        return extraGwAccountMapper.listByLimit(startRow, maxRows);
    }
}
