package com.springboot.xp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.springboot.xp.filter.SessionFilter;

/**
该注解指定项目为springboot，由此类当作程序入口
自动装配 web 依赖的环境

**/
@SpringBootApplication
@MapperScan("com.springboot.xp.dao")      // MyBatis Mapper接口文件所在包路径
public class SpringbootApplication extends SpringBootServletInitializer{

   public static void main(String[] args) {
       SpringApplication.run(SpringbootApplication.class, args);
   }
   
   @Bean
   public FilterRegistrationBean registFilter() {
       FilterRegistrationBean registration = new FilterRegistrationBean();
       registration.setFilter(new SessionFilter());
       registration.addUrlPatterns("/*");
       registration.setName("sessionFilter");
       registration.setOrder(1);
       return registration;
   }
   
   
   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
       return builder.sources(this.getClass());
   }
   
}
