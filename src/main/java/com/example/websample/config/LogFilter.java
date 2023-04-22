package com.example.websample.config;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
// @Component   // 필터가 한개일때는 컴포넌트로 등록 (직접 자동등록)
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 외부 -> filter "-> 처리 ->" filter -> 외부   "" 이 필터체인이 하는 부분
        log.info("Hello Logfilter : " + Thread.currentThread());  // 필터로 들어오기 전 로그찍기
        chain.doFilter(request, response);  // 실제 처리
        log.info("Bye Logfilter : " + Thread.currentThread());  // 필터 처리 후 로그 찍기
        // 디버그 실행 후 http://localhost:8080/order/300 웹에 검색하면 콘솔창이 뜸
    }
}
