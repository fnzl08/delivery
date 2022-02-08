package com.sparta.delivery.repository;

import com.sparta.delivery.domain.Food;
import com.sparta.delivery.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findFoodsByRestaurant(Restaurant restaurant);
    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);

    Boolean existsByNameAndRestaurantId(String foodName, Long restaurantId);
}
