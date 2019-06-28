package com.springboot.xp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.springboot.xp.bean.AuthSession;
import com.springboot.xp.controller.BaseController;

@Component
public class SessionFilter implements Filter {

    private List<Pattern> whiteUrlList = new ArrayList<Pattern>();

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        whiteUrlList.add(Pattern.compile("/hello"));
        whiteUrlList.add(Pattern.compile("/mongo/.+$"));
        whiteUrlList.add(Pattern.compile("user/login"));
        System.out.println("=====session filter init whiteUrlList done=====");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        boolean isFree = false;
        // 过滤掉不需要登录请求通过的路径
        for (Pattern pattern : whiteUrlList) {
            Matcher matcher = pattern.matcher(request.getRequestURI());
            if (matcher.find()) {
                isFree = true;
                break;
            }
        }
        if (isFree) {
            filterChain.doFilter(request, response);
            return;
        }
        // 是否登录
        HttpSession session = request.getSession();
        AuthSession authSession = (AuthSession) session.getAttribute(BaseController.AUTH_SESSION);
        if (null == authSession) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            return;
        }
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        whiteUrlList = null;
    }

}
