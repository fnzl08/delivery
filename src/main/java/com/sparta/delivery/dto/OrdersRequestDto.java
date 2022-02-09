package com.sparta.delivery.dto;

import com.sparta.delivery.domain.OrderDetail;
import lombok.Getter;

import java.util.List;

//주문디테일 할 때 여기서 가져올거. 식당 아이디랑 푸드에 foodid, queantity
@Getter
public class OrdersRequestDto {
    private Long restaurantId;
    private List<OrderDetail> Foods;
}
