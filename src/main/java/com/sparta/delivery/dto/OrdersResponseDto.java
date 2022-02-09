package com.sparta.delivery.dto;

import com.sparta.delivery.domain.Orders;
import lombok.Getter;

import java.util.List;

//주문하기 해서 반환한거. resname이랑 foods(name, quantity, price), fee, totalprice
@Getter
public class OrdersResponseDto {
    private String retaurantName;
    private List<FoodsResponseDto> foods;
    private Long deliveryFee;
    private Long totalPrice;

    public OrdersResponseDto(List<FoodsResponseDto> foods, Orders orders, Long deliveryFee){
        this.retaurantName = orders.getRestaurantName();
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = orders.getTotalPrice();
    }
}
