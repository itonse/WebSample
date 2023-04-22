package com.example.websample.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {   // WebConfig에서 등록 필요
    // 필터보다 더 다양한 처리 가능해서 필터보다 많이 쓰는 추세.
    // preHandle, postHandle, afterCompletion 이 디폴트로 만들어져있긴 함.

    // 시작: 내부서블릿으로 들어와서 DispatcherServlet이
    // 이 요청을 어떤 Controller로 보낼지 어떤 Handler 한테 넘겨줄지  정해진 상황
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("preHandle LogInterceptor : " + Thread.currentThread());   // 현재 스레드 찍기
        log.info("preHandle handler : " + handler);  // 이 요청을 누가 처리할지 로그에서 확인 가능

        return true;
    }

    // ↓ 반대로 타고 나옴

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {    // 어떤 뷰를 그려줄지, 어떤 데이터가 들어갈지 확인 가능
        log.info("postHandle LogInterceptor : " + Thread.currentThread());  // 현재 스레드 찍기
    }

    // ↓ 처리 여부 상관없이 항상 거침

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {   // 처리되던, 실패하던 실행하니 예외처리
        log.info("afterCompletion LogInterceptor : " + Thread.currentThread());

        if (ex != null) {  // 예외 발생시
            log.error("afterCompetion exception : " + ex.getMessage()); // 에러메세지를 로그로 찍기
        }
    }
}
