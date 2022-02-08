package com.sparta.delivery.dto;

import com.sparta.delivery.domain.OrderDetail;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRequestDto {
    private Long restaurantId;
    private List<OrderDetail> Foods;
}
