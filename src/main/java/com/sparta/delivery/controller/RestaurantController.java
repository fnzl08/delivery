package com.sparta.delivery.controller;

import com.sparta.delivery.domain.Restaurant;
import com.sparta.delivery.dto.RestaurantRequestDto;
import com.sparta.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    //식당 추가
    @PostMapping("/restaurant/register")
    public Restaurant addRestaurant(
            @RequestBody RestaurantRequestDto requestDto
    ){
        return restaurantService.addRestaurant(requestDto);
    }

    //식당 조회
    @GetMapping("/restaurants")
    public List<Restaurant> findAllRestaurant() {

        return restaurantService.findAllRestaurant();
    }
}
