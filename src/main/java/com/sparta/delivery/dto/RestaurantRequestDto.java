package com.sparta.delivery.dto;

import lombok.Getter;

//음식점 등록시 들어오고 들어갈거
@Getter
public class RestaurantRequestDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
