package com.sparta.delivery.dto;

import com.sparta.delivery.domain.OrderDetail;
import lombok.Getter;

import java.util.List;

//주문디테일 할 때 여기서 가져올거. 식당 아이디랑 푸드에 foodid, queantity
@Getter
public class OrdersRequestDto {
    private Long restaurantId;
    //Foods로 해서 오류났었다. 가 아니네 아직 오류난다고..? 이 다음 억지로 Long 넣은걸 다 int로 고쳐주니 500에러는 지났는데
    //주문하기랑 주문조회가 여전히 안된다.
    private List<OrderDetail> foods;
}
