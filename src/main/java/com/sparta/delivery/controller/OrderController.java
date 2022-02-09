package com.sparta.delivery.controller;


import com.sparta.delivery.dto.OrdersRequestDto;
import com.sparta.delivery.dto.OrdersResponseDto;
import com.sparta.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//자 오더컨트롤러 시작할거임. 오더 서비스로 보낼거니까
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //주문하기. Requestdto에 레스토랑 id, foods(foodid, quantity, price 들어있음)있었음. 포스트니까 반환값 없다.
    @PostMapping("/order/request")
    public OrdersResponseDto orderFood(
            @RequestBody OrdersRequestDto ordersRequestDto
    ){
        return orderService.orderFood(ordersRequestDto);
    }


    @GetMapping("/orders")
    public List<OrdersResponseDto> findAllOrder() {

        return orderService.findAllOrder();
    }
}