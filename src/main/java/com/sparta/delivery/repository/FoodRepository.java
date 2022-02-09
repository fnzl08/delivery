package com.sparta.delivery.repository;

import com.sparta.delivery.domain.Food;
import com.sparta.delivery.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    //레스토랑에 들어가있는 foods 리스트화
    List<Food> findFoodsByRestaurant(Restaurant restaurant);

    //레스토랑 안에 들어가있는 foods 중 name으로 찾은 food
    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);
}
