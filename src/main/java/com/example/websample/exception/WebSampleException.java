package com.example.websample.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor  // 간단하게 생성자와
@Data  // 게터세터를 생성
public class WebSampleException extends RuntimeException{
    // 그냥 익셉션이 아닌 WebSampleException: RuntimeException을 상속받은 '자식인셉션'
    private ErrorCode errorCode;  // 직접만든 커스텀 에러코드와
    private String message;  // 메세지를 담음
}
