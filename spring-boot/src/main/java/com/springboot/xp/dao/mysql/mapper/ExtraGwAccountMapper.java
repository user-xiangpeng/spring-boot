package com.springboot.xp.dao.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springboot.xp.dao.mysql.model.GwAccount;

public interface ExtraGwAccountMapper {

    // @Select("SELECT * FROM T_GW_ACCOUNT LIMIT #{startRow}, #{maxRows}")
    List<GwAccount> listByLimit(@Param("startRow") int startRow, @Param("maxRows") int maxRows);
}