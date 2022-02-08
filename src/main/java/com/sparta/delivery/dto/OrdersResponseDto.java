package com.sparta.delivery.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrdersResponseDto {
    private String retaurantName;
    private List<FoodsResponseDto> foods;
    private Long deliveryFee;
    private Long totalPrice;
}
