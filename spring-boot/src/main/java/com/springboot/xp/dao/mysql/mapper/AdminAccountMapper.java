package com.springboot.xp.dao.mysql.mapper;

import com.springboot.xp.dao.mysql.model.AdminAccount;
import com.springboot.xp.dao.mysql.model.AdminAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_ACCOUNT
     *
     * @mbggenerated Thu Jul 18 14:06:16 CST 2019
     */
    int countByExample(AdminAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_ACCOUNT
     *
     * @mbggenerated Thu Jul 18 14:06:16 CST 2019
     */
    int deleteByExample(AdminAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_ACCOUNT
     *
     * @mbggenerated Thu Jul 18 14:06:16 CST 2019
     */
    int deleteByPrimaryKey(String empId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_ACCOUNT
     *
     * @mbggenerated Thu Jul 18 14:06:16 CST 2019
     */
    int insert(AdminAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_ACCOUNT
     *
     * @mbggenerated Thu Jul 18 14:06:16 CST 2019
     */
    int insertSelective(AdminAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_ACCOUNT
     *
     * @mbggenerated Thu Jul 18 14:06:16 CST 2019
     */
    List<AdminAccount> selectByExample(AdminAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_ACCOUNT
     *
     * @mbggenerated Thu Jul 18 14:06:16 CST 2019
     */
    AdminAccount selectByPrimaryKey(String empId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_ACCOUNT
     *
     * @mbggenerated Thu Jul 18 14:06:16 CST 2019
     */
    int updateByExampleSelective(@Param("record") AdminAccount record, @Param("example") AdminAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_ACCOUNT
     *
     * @mbggenerated Thu Jul 18 14:06:16 CST 2019
     */
    int updateByExample(@Param("record") AdminAccount record, @Param("example") AdminAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_ACCOUNT
     *
     * @mbggenerated Thu Jul 18 14:06:16 CST 2019
     */
    int updateByPrimaryKeySelective(AdminAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_ACCOUNT
     *
     * @mbggenerated Thu Jul 18 14:06:16 CST 2019
     */
    int updateByPrimaryKey(AdminAccount record);
}