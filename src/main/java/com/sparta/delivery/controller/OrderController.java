package com.sparta.delivery.controller;


import com.sparta.delivery.dto.OrdersRequestDto;
import com.sparta.delivery.dto.OrdersResponseDto;
//import com.sparta.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class OrderController {
//    private final OrderService orderService;
//
//    @PostMapping("/order/request")
//    public OrdersResponseDto orderFood(
//            @RequestBody OrdersRequestDto ordersRequestDto
//    ){
//        return orderService.putOrder(ordersRequestDto);
//    }
//
//
//    @GetMapping("/orders")
//    public List<OrdersResponseDto> findAllOrder() {
//        return orderService.findAllOrder();
//    }
//}