package com.example.websample.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;

@Configuration   // 필터가 하나 이상일 때: 더욱 구체적으로 설정
public class WebConfig {

    @Bean
    public FilterRegistrationBean loggingFilter() {  // FilterRegistrationBean: 필터를 등록해주기 위한 빈
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());  // 필터를 하나 만들어서 RegistrationBean 에 등록
        filterRegistrationBean.setOrder(1);   // 필터가 다중으로 있을 때 첫번째로 뜸 (필터의 동작 순서지정)
        filterRegistrationBean.addUrlPatterns("/*");   // 특정 URL패턴에 등록. (*: 전체)

        return filterRegistrationBean;
    }
}
