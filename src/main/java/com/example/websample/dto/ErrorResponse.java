package com.example.websample.dto;

import com.example.websample.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor  // 자동으로 생성자를 만들어줌
@Data  // getter&setter 를 쉽게 만들어줌
public class ErrorResponse {  //
    private ErrorCode errorCode;   // 에러코드가 직접 들어감
    private String message;
}
