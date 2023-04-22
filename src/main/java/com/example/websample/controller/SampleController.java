package com.example.websample.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j   // 아래 log 등 편의성을 제공해주는 라이브러리
@RestController    // 레스트컨트롤러: 응답값으로 Rest API 요청에 대한 응답(주로 JSON)을 주도록 함
public class SampleController {
    // 스프링 MVC 기본 HTTP 요청 매핑
    @GetMapping ("/order/{orderId}") // {orderId}로 987 사용  // 초기에 지구본 눌러서 http 클라이언트 생성하기
    public String getOrder(@PathVariable("orderId") String id) throws IllegalAccessException {  // 파라미터 넘기기: PathVariable
        log.info("Get some order : " + id);   // 겟 썸 오더 로그찍음

        if ("500".equals(id)) {
            throw new IllegalAccessException("500 is not valid orderId.");
        }

        return "orderId:" + id + ", " + "orderAmount:1000";   // path 값도 받아올 수 있음
    }

    @DeleteMapping ("/order/{orderId}")   // ID 987번을 지우는 주문
    public String deleteOrder(@PathVariable("orderId") String id) {
        log.info("Delete some order : " + id);
        return "Delete orderId:" + id;
    }

    @GetMapping ("/order")
    public String getOrderWithRequestParam(    // 쿼리파람: 게시판의 검색 필터 페이징에서 많이 사용
            @RequestParam(value = "orderId", required = false, defaultValue = "defaultId") String id,  // URL 뒤에 ? 로 여러개의 값을 받아오고 싶을때는 리퀘스트파람
            @RequestParam("orderAmount") Integer amount) {  // order 파마미터에 들어오는 RequestParam 들이 id, amount에 들어옴.
        log.info("Get order : " + id + ", amount : " + amount);
        return "orderId:" + id + ", " + "orderAmount:" + amount;  // 입력해준 id와 amount 들어옴
    }

    @PostMapping ("/order")
    public String createOrder(
            @RequestBody CreateOrderRequest createOrderRequest,  // 리퀘스트바디는 파라미터로 보내야되는 데이터양이 클때 사용(json으로 보냄)
            @RequestHeader String userAccountId) {   // 리퀘스트헤더로 헤더 정보를 편하게 넘김(account800)
        log.info("Create order : " + createOrderRequest +
                ", userAccountId : " + userAccountId);  // userAccountId는 중요한 정보니 로그로만 찍어줌.
        return "orderId:" + createOrderRequest.getOrderId() + ", " +
                "orderAmount:" + createOrderRequest.getOrderAmount();
        // 맵으로 받게되면 키를 일일히 타이핑 해야되서 불편: json(key:value)으로 받기
    }

    @PutMapping ("/order")   // post: 내용을 보내서 저장을 시킴  put: 저장된 내용을 전체를 이것으로 싹 바꾸기, patch: 일부분 수정
    public String createOrder() {
        log.info("Create order");
        return "order created -> orderId:1, orderAmount:1000";
    }

    @Data  // getter&setter 생성 필요 없이 요것만 붙여서 아주 심플하게 이 클래스를 자바빈 객체로 만듦.
    public static class CreateOrderRequest {
        private String orderId;
        private Integer orderAmount;   // 요 두 필드의 값을 가지는 자바빈 만들기


    }
}
