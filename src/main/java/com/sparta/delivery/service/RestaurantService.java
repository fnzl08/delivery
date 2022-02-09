package com.sparta.delivery.service;

import com.sparta.delivery.domain.Restaurant;
import com.sparta.delivery.dto.RestaurantRequestDto;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;


    @Transactional
    //addRes 함수 돌릴거임. controller로 request에 넣어놓은 min, fee 꺼내와서 보자
    public Restaurant addRestaurant(RestaurantRequestDto requestDto) {

        int minOrderPrice = requestDto.getMinOrderPrice();
        Long deliveryFee = requestDto.getDeliveryFee();

        if (1000 > minOrderPrice || minOrderPrice > 100000) {
            throw new IllegalArgumentException("최소주문 가격 허용값을 벗어났습니다.");
        }
        if (minOrderPrice % 100 != 0) {
            throw new IllegalArgumentException("최소주문 가격은 100원 단위로 입력해야 합니다.");
        }
        if ((0 > deliveryFee) || (deliveryFee > 10_000)) {
            throw new IllegalArgumentException("배달비는 0원 이상 10000원 이하입니다.");
        }
        if (deliveryFee % 500 != 0) {
            throw new IllegalArgumentException("배달비는 500원 단위로 입력해야 합니다.");
        }

        Restaurant restaurant = Restaurant.builder()
                .name(requestDto.getName())
                .minOrderPrice(minOrderPrice)
                .deliveryFee(requestDto.getDeliveryFee())
                .build();

        restaurantRepository.save(restaurant);

        return restaurant;
    }

    @Transactional
    public List<Restaurant> findAllRestaurant(){

        return restaurantRepository.findAll();
    }
}
