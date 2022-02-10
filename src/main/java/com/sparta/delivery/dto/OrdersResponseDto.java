package com.sparta.delivery.dto;

import com.sparta.delivery.domain.Orders;
import lombok.Getter;

import java.util.List;

//주문하기 해서 반환한거. resname이랑 foods(name, quantity, price), fee, totalprice
@Getter
public class OrdersResponseDto {
    private String restaurantName;
    private List<FoodsResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;

    public OrdersResponseDto(List<FoodsResponseDto> foods, Orders orders, int deliveryFee){
        this.restaurantName = orders.getRestaurantName();
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = orders.getTotalPrice();
    }
}
