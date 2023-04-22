package com.example.websample.exception;

import com.example.websample.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {   // 깔끔하게 모든 컨트롤러에 동일한 익셉션을 추가 (다중 컨트롤러에서 익셉션 관리하기 쉬움).(AOP와 비슷)
    // 현업에서는 보통 커스텀 익셉션을 만들어서 (에러코드를 enum으로 만듦) 에러처리 하는것을 선호.
    // 단일 컨트롤러에 있던 예외처리기들을 모두 가져옴.
    @ExceptionHandler(IllegalAccessException.class)  // 이렇게 특정 익셉션들은 직접 핸들러를 만들어서 처리.
    public ResponseEntity<ErrorResponse> handleIllegalAccessException(  // ResponseEntity로 감싸기: 헤더, status 지정 등 다양한 기능 추가
                                                                        IllegalAccessException e) {  // 일레갈 익센션을 인자로 받음
        log.error("IllegalAccessException is occurred.", e);  // 익셉션 발생했다는 로그 찍음

        return ResponseEntity   // 여러가지 생성하는 방법들 제공: 빌더 사용
                .status(HttpStatus.FORBIDDEN)  // 잘못된 접근이라서 넣어줌. 응답의 코드값이 200이 아닌 403으로 내려감
                .body(new ErrorResponse(ErrorCode.TOO_BIG_ID_ERROR,  // 커스텀익셉션의 에러코드 활용
                        "IllegalAccessException is occurred."));   // 웹화면에 깔끔하게 json(키:값)으로 응답값이 들어감
    }

    @ExceptionHandler(WebSampleException.class)
    public ResponseEntity<ErrorResponse> handleWebSampleException(
            WebSampleException e) {   // 웹샘플익센션을 받음
        log.error("WebSampleException is occurred.", e);  // 웹샘플익셉션 발생!

        return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)  //
                .body(new ErrorResponse(e.getErrorCode(),  // 정확한 에러코드를 가져옴.
                        "WebSampleException is occurred."));
    }

    @ExceptionHandler(Exception.class)  // 모든 예외의 부모: 그 외에 나머지 익셉션들을 전부 다 처리 해줌. 무조건 필요(최후의 보루)
    // 해당 익셉션 -> (없으면)그 익셉션의 부모 -> (없으면)모든 예외의 부모
    public ResponseEntity<ErrorResponse> handleException(
            Exception e) {   // 웹샘플익센션을 받음
        log.error("Exception is occurred.", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)  //
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR,  // 정확한 에러코드를 가져옴.
                        "Exception is occurred."));
    }
}
