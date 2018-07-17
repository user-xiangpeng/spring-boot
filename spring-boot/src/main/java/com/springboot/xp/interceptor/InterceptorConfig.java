package com.springboot.xp.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 添加自己的拦截器
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
	
	

	
}
