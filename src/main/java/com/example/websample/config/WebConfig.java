package com.example.websample.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration   // 필터가 하나 이상일 때: 더욱 구체적으로 설정
public class WebConfig  implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean loggingFilter() {  // FilterRegistrationBean: 필터를 등록해주기 위한 빈
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());  // 필터를 하나 만들어서 RegistrationBean 에 등록
        filterRegistrationBean.setOrder(1);   // 필터가 다중으로 있을 때 첫번째로 뜸 (필터의 동작 순서지정)
        filterRegistrationBean.addUrlPatterns("/*");   // 특정 URL패턴에 등록. (*: 전체)

        return filterRegistrationBean;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 만들었던 LogInterceptor를 웹 영역에 WebMvcConfigurer를 통해 등록
        registry.addInterceptor(new LogInterceptor())  // registry: 등록한 내역들을 관리하는 등록부. 여기에 인터셉터 추가
                .order(1)  // 순서가 첫번째
                .addPathPatterns("/**")  // 어떤거에 이 인터셉터는 추가할 지
                .excludePathPatterns("/css/*", "/images/*");  // 어떤거에서 인터셉터를 뺄지 (저런 정적 파일들은 인터셉터를 탈 이유가 업음)
    }
}
