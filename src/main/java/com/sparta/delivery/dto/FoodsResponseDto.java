package com.sparta.delivery.dto;

import com.sparta.delivery.domain.OrderDetail;
import lombok.Getter;

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
