package com.springboot.xp.service;

import java.util.List;

import com.springboot.xp.dao.mysql.model.GwAccount;

public interface IGwAccountService {

    /**
     * 分页查
     * 
     * @param startRow
     * @param maxRows
     * @return
     */
    List<GwAccount> listByLimit(int startRow, int maxRows);
}
