package com.springboot.xp.bean;

import com.springboot.xp.dao.mysql.model.AdminAccount;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class AuthSession implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4561920140573049754L;

    private String userId;
    private String sessionId;
    private String name;
    private String emaill;

    public AuthSession(HttpServletRequest request, AdminAccount account) {
        this.userId = account.getEmpId();
        this.sessionId = request.getSession().getId();
        this.name = account.getEmpName();
        this.emaill = account.getEmpEmail();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }


}
