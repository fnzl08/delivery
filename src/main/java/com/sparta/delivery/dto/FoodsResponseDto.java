package com.sparta.delivery.dto;

import com.sparta.delivery.domain.OrderDetail;
import lombok.Getter;

//주문에 들어갈거. foods에 음식이름, 양, 가격
@Getter
public class FoodsResponseDto {
    private String name;
    private int quantity;
    private int price;

    public FoodsResponseDto(OrderDetail orderDetail){
        this.name = orderDetail.getName();
        this.quantity = orderDetail.getQuantity();
        this.price = orderDetail.getPrice();
    }
}
