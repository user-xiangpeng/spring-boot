package com.springboot.xp.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

public class LoggerInterceptor implements HandlerInterceptor {
	
	private final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("host", request.getRemoteHost());
		map.put("localPort", request.getLocalPort());
		map.put("method", request.getMethod());
		map.put("sessionId", request.getSession().getId());
		logger.info(JSONObject.toJSONString(map));
		return true;
	}

}
