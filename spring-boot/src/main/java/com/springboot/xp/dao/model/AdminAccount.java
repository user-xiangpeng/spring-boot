package com.springboot.xp.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "T_ADMIN_ACCOUNT")
public class AdminAccount {
    /**
     * 员工ID
     */
    @Id
    @Column(name = "EMP_ID")
    private String empId;

    /**
     * 员工姓名
     */
    @Column(name = "EMP_NAME")
    private String empName;

    /**
     * 员工邮箱
     */
    @Column(name = "EMP_EMAIL")
    private String empEmail;

    /**
     * 员工密码
     */
    @Column(name = "EMP_PWD")
    private String empPwd;

    /**
     * 员工电话
     */
    @Column(name = "EMP_PHONE")
    private String empPhone;

    /**
     * AM工作电话-座机号
     */
    @Column(name = "EMP_MOBILE")
    private String empMobile;

    /**
     * 账号类别,1:运维管理员，2：AM 项目经理,3AM客户经理
     */
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    /**
     * 是否生效,0：无效，1：有效
     */
    @Column(name = "IS_LIVE")
    private String isLive;

    /**
     * 创建者
     */
    @Column(name = "CREATOR")
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_DTTM")
    private Date createDttm;

    /**
     * 更新者
     */
    @Column(name = "UPDATER")
    private String updater;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_DTTM")
    private Date updateDttm;

    /**
     * AM端企业需要支付的金额
     */
    @Column(name = "AM_PAY_MONEY")
    private Long amPayMoney;

    /**
     * am对应候选人上岗人数
     */
    @Column(name = "AM_ENTRY_CONFIRM_NUM")
    private Long amEntryConfirmNum;

    /**
     * 回款总金额
     */
    @Column(name = "PAYMENTS_AMOUNT_COUNT")
    private Long paymentsAmountCount;

    /**
     *  回款人数
     */
    @Column(name = "PAYMENTS_NUM")
    private Long paymentsNum;

    /**
     * 职位动态信息阅读时间
     */
    @Column(name = "JOB_DYNAMIC_INFORMATION_READTIME")
    private Date jobDynamicInformationReadtime;

    /**
     * 候选人动态阅读时间
     */
    @Column(name = "CANDIDATE_DYNAMIC_READTIME")
    private Date candidateDynamicReadtime;

    /**
     * 职位沟通时间
     */
    @Column(name = "JOB_CONMUNICATION_TIME")
    private Date jobConmunicationTime;

    /**
     * 头像
     */
    @Column(name = "URL")
    private String url;

    /**
     * 上一次登录时间
     */
    @Column(name = "PREV_LOGIN_TIME")
    private Date prevLoginTime;

    /**
     * 当前登录时间
     */
    @Column(name = "NOW_LOGIN_TIME")
    private Date nowLoginTime;

    /**
     * 员工微信
     */
    @Column(name = "EMP_WECHAT")
    private String empWechat;

    /**
     * 员工QQ
     */
    @Column(name = "EMP_QQ")
    private String empQq;

    /**
     * AM级别
     */
    @Column(name = "EMP_LEVEL")
    private Integer empLevel;

    /**
     * 角色id
     */
    @Column(name = "ROLE_ID")
    private Long roleId;

    /**
     * 职能，1：AM；2：PAM；3：HM
     */
    @Column(name = "DUTIES")
    private String duties;

    /**
     * 角色细分
     */
    @Column(name = "ROLE_SUBSECTION")
    private Long roleSubsection;

    /**
     * 状态（1：试用期；2：正式；3：离职）
     */
    @Column(name = "STATUS")
    private Integer status;

    /**
     * 职级
     */
    @Column(name = "POSITION_RANK")
    private String positionRank;

    /**
     * 积分
     */
    @Column(name = "SCORE")
    private Double score;

    /**
     * 冻结时间
     */
    @Column(name = "FROZEN_TIME")
    private Date frozenTime;

    /**
     * ehr员工id
     */
    @Column(name = "EHR_STAFF_ID")
    private Long ehrStaffId;

    /**
     * 获取员工ID
     *
     * @return EMP_ID - 员工ID
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * 设置员工ID
     *
     * @param empId 员工ID
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * 获取员工姓名
     *
     * @return EMP_NAME - 员工姓名
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * 设置员工姓名
     *
     * @param empName 员工姓名
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * 获取员工邮箱
     *
     * @return EMP_EMAIL - 员工邮箱
     */
    public String getEmpEmail() {
        return empEmail;
    }

    /**
     * 设置员工邮箱
     *
     * @param empEmail 员工邮箱
     */
    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    /**
     * 获取员工密码
     *
     * @return EMP_PWD - 员工密码
     */
    public String getEmpPwd() {
        return empPwd;
    }

    /**
     * 设置员工密码
     *
     * @param empPwd 员工密码
     */
    public void setEmpPwd(String empPwd) {
        this.empPwd = empPwd;
    }

    /**
     * 获取员工电话
     *
     * @return EMP_PHONE - 员工电话
     */
    public String getEmpPhone() {
        return empPhone;
    }

    /**
     * 设置员工电话
     *
     * @param empPhone 员工电话
     */
    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    /**
     * 获取AM工作电话-座机号
     *
     * @return EMP_MOBILE - AM工作电话-座机号
     */
    public String getEmpMobile() {
        return empMobile;
    }

    /**
     * 设置AM工作电话-座机号
     *
     * @param empMobile AM工作电话-座机号
     */
    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    /**
     * 获取账号类别,1:运维管理员，2：AM 项目经理,3AM客户经理
     *
     * @return ACCOUNT_TYPE - 账号类别,1:运维管理员，2：AM 项目经理,3AM客户经理
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * 设置账号类别,1:运维管理员，2：AM 项目经理,3AM客户经理
     *
     * @param accountType 账号类别,1:运维管理员，2：AM 项目经理,3AM客户经理
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * 获取是否生效,0：无效，1：有效
     *
     * @return IS_LIVE - 是否生效,0：无效，1：有效
     */
    public String getIsLive() {
        return isLive;
    }

    /**
     * 设置是否生效,0：无效，1：有效
     *
     * @param isLive 是否生效,0：无效，1：有效
     */
    public void setIsLive(String isLive) {
        this.isLive = isLive;
    }

    /**
     * 获取创建者
     *
     * @return CREATOR - 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_DTTM - 创建时间
     */
    public Date getCreateDttm() {
        return createDttm;
    }

    /**
     * 设置创建时间
     *
     * @param createDttm 创建时间
     */
    public void setCreateDttm(Date createDttm) {
        this.createDttm = createDttm;
    }

    /**
     * 获取更新者
     *
     * @return UPDATER - 更新者
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * 设置更新者
     *
     * @param updater 更新者
     */
    public void setUpdater(String updater) {
        this.updater = updater;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_DTTM - 更新时间
     */
    public Date getUpdateDttm() {
        return updateDttm;
    }

    /**
     * 设置更新时间
     *
     * @param updateDttm 更新时间
     */
    public void setUpdateDttm(Date updateDttm) {
        this.updateDttm = updateDttm;
    }

    /**
     * 获取AM端企业需要支付的金额
     *
     * @return AM_PAY_MONEY - AM端企业需要支付的金额
     */
    public Long getAmPayMoney() {
        return amPayMoney;
    }

    /**
     * 设置AM端企业需要支付的金额
     *
     * @param amPayMoney AM端企业需要支付的金额
     */
    public void setAmPayMoney(Long amPayMoney) {
        this.amPayMoney = amPayMoney;
    }

    /**
     * 获取am对应候选人上岗人数
     *
     * @return AM_ENTRY_CONFIRM_NUM - am对应候选人上岗人数
     */
    public Long getAmEntryConfirmNum() {
        return amEntryConfirmNum;
    }

    /**
     * 设置am对应候选人上岗人数
     *
     * @param amEntryConfirmNum am对应候选人上岗人数
     */
    public void setAmEntryConfirmNum(Long amEntryConfirmNum) {
        this.amEntryConfirmNum = amEntryConfirmNum;
    }

    /**
     * 获取回款总金额
     *
     * @return PAYMENTS_AMOUNT_COUNT - 回款总金额
     */
    public Long getPaymentsAmountCount() {
        return paymentsAmountCount;
    }

    /**
     * 设置回款总金额
     *
     * @param paymentsAmountCount 回款总金额
     */
    public void setPaymentsAmountCount(Long paymentsAmountCount) {
        this.paymentsAmountCount = paymentsAmountCount;
    }

    /**
     * 获取 回款人数
     *
     * @return PAYMENTS_NUM -  回款人数
     */
    public Long getPaymentsNum() {
        return paymentsNum;
    }

    /**
     * 设置 回款人数
     *
     * @param paymentsNum  回款人数
     */
    public void setPaymentsNum(Long paymentsNum) {
        this.paymentsNum = paymentsNum;
    }

    /**
     * 获取职位动态信息阅读时间
     *
     * @return JOB_DYNAMIC_INFORMATION_READTIME - 职位动态信息阅读时间
     */
    public Date getJobDynamicInformationReadtime() {
        return jobDynamicInformationReadtime;
    }

    /**
     * 设置职位动态信息阅读时间
     *
     * @param jobDynamicInformationReadtime 职位动态信息阅读时间
     */
    public void setJobDynamicInformationReadtime(Date jobDynamicInformationReadtime) {
        this.jobDynamicInformationReadtime = jobDynamicInformationReadtime;
    }

    /**
     * 获取候选人动态阅读时间
     *
     * @return CANDIDATE_DYNAMIC_READTIME - 候选人动态阅读时间
     */
    public Date getCandidateDynamicReadtime() {
        return candidateDynamicReadtime;
    }

    /**
     * 设置候选人动态阅读时间
     *
     * @param candidateDynamicReadtime 候选人动态阅读时间
     */
    public void setCandidateDynamicReadtime(Date candidateDynamicReadtime) {
        this.candidateDynamicReadtime = candidateDynamicReadtime;
    }

    /**
     * 获取职位沟通时间
     *
     * @return JOB_CONMUNICATION_TIME - 职位沟通时间
     */
    public Date getJobConmunicationTime() {
        return jobConmunicationTime;
    }

    /**
     * 设置职位沟通时间
     *
     * @param jobConmunicationTime 职位沟通时间
     */
    public void setJobConmunicationTime(Date jobConmunicationTime) {
        this.jobConmunicationTime = jobConmunicationTime;
    }

    /**
     * 获取头像
     *
     * @return URL - 头像
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置头像
     *
     * @param url 头像
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取上一次登录时间
     *
     * @return PREV_LOGIN_TIME - 上一次登录时间
     */
    public Date getPrevLoginTime() {
        return prevLoginTime;
    }

    /**
     * 设置上一次登录时间
     *
     * @param prevLoginTime 上一次登录时间
     */
    public void setPrevLoginTime(Date prevLoginTime) {
        this.prevLoginTime = prevLoginTime;
    }

    /**
     * 获取当前登录时间
     *
     * @return NOW_LOGIN_TIME - 当前登录时间
     */
    public Date getNowLoginTime() {
        return nowLoginTime;
    }

    /**
     * 设置当前登录时间
     *
     * @param nowLoginTime 当前登录时间
     */
    public void setNowLoginTime(Date nowLoginTime) {
        this.nowLoginTime = nowLoginTime;
    }

    /**
     * 获取员工微信
     *
     * @return EMP_WECHAT - 员工微信
     */
    public String getEmpWechat() {
        return empWechat;
    }

    /**
     * 设置员工微信
     *
     * @param empWechat 员工微信
     */
    public void setEmpWechat(String empWechat) {
        this.empWechat = empWechat;
    }

    /**
     * 获取员工QQ
     *
     * @return EMP_QQ - 员工QQ
     */
    public String getEmpQq() {
        return empQq;
    }

    /**
     * 设置员工QQ
     *
     * @param empQq 员工QQ
     */
    public void setEmpQq(String empQq) {
        this.empQq = empQq;
    }

    /**
     * 获取AM级别
     *
     * @return EMP_LEVEL - AM级别
     */
    public Integer getEmpLevel() {
        return empLevel;
    }

    /**
     * 设置AM级别
     *
     * @param empLevel AM级别
     */
    public void setEmpLevel(Integer empLevel) {
        this.empLevel = empLevel;
    }

    /**
     * 获取角色id
     *
     * @return ROLE_ID - 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取职能，1：AM；2：PAM；3：HM
     *
     * @return DUTIES - 职能，1：AM；2：PAM；3：HM
     */
    public String getDuties() {
        return duties;
    }

    /**
     * 设置职能，1：AM；2：PAM；3：HM
     *
     * @param duties 职能，1：AM；2：PAM；3：HM
     */
    public void setDuties(String duties) {
        this.duties = duties;
    }

    /**
     * 获取角色细分
     *
     * @return ROLE_SUBSECTION - 角色细分
     */
    public Long getRoleSubsection() {
        return roleSubsection;
    }

    /**
     * 设置角色细分
     *
     * @param roleSubsection 角色细分
     */
    public void setRoleSubsection(Long roleSubsection) {
        this.roleSubsection = roleSubsection;
    }

    /**
     * 获取状态（1：试用期；2：正式；3：离职）
     *
     * @return STATUS - 状态（1：试用期；2：正式；3：离职）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态（1：试用期；2：正式；3：离职）
     *
     * @param status 状态（1：试用期；2：正式；3：离职）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取职级
     *
     * @return POSITION_RANK - 职级
     */
    public String getPositionRank() {
        return positionRank;
    }

    /**
     * 设置职级
     *
     * @param positionRank 职级
     */
    public void setPositionRank(String positionRank) {
        this.positionRank = positionRank;
    }

    /**
     * 获取积分
     *
     * @return SCORE - 积分
     */
    public Double getScore() {
        return score;
    }

    /**
     * 设置积分
     *
     * @param score 积分
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * 获取冻结时间
     *
     * @return FROZEN_TIME - 冻结时间
     */
    public Date getFrozenTime() {
        return frozenTime;
    }

    /**
     * 设置冻结时间
     *
     * @param frozenTime 冻结时间
     */
    public void setFrozenTime(Date frozenTime) {
        this.frozenTime = frozenTime;
    }

    /**
     * 获取ehr员工id
     *
     * @return EHR_STAFF_ID - ehr员工id
     */
    public Long getEhrStaffId() {
        return ehrStaffId;
    }

    /**
     * 设置ehr员工id
     *
     * @param ehrStaffId ehr员工id
     */
    public void setEhrStaffId(Long ehrStaffId) {
        this.ehrStaffId = ehrStaffId;
    }
}