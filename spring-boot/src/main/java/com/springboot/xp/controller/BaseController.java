package com.springboot.xp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springboot.xp.bean.AuthSession;
import com.springboot.xp.exception.BaseException;

public class BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    public static final String AUTH_SESSION = "authSession";

    protected String checkLogin(HttpServletRequest request) throws BaseException {
        HttpSession session = request.getSession();
        AuthSession authSession = (AuthSession) session.getAttribute(AUTH_SESSION);
        if (null == authSession) {
            throw new BaseException(BaseException.NO_LOGIN);
        }
        return authSession.getUserId();
    }

    /**
     *
     * 功能描述: <br>
     * 输出文件
     *
     * @param response
     * @param fileName 文件名
     * @param file 文件
     * @throws UnsupportedEncodingException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    protected void outputFile(HttpServletResponse response, String fileName, byte[] file)
            throws UnsupportedEncodingException {
        if (ArrayUtils.isEmpty(file)) {
            logger.info("get file download url failed.");
            return;
        }
        // 设置相关头信息
        response.setContentType("multipart/form-data");
        // fileName = new String(fileName.getBytes("iso-8859-1"), Constants.UTF8);
        // 文件名编码
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("fileName encode failed. errorMessage[{}]", e.getMessage());
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.addDateHeader("Expries", 0);
        // 返回文件
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(file);
            out.flush();
        } catch (IOException e) {
            logger.error("download file failed. errorMessage[{}]", e.getMessage());
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("OutputStream close failed. errorMessage[{}]", e.getMessage());
                }
            }
        }
    }

}
