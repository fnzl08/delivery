package com.sparta.delivery.controller;

import com.sparta.delivery.domain.Food;
import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성. 주로 의존성 주입 편의성을 위해서 사용
@RequiredArgsConstructor

//foodcontroller에 대해서 실행하겠다.
public class FoodController {

    //foodservice로 간다
    private final FoodService foodService;

    //음식 추가
    @PostMapping("/restaurant/{restaurantId}/food/register")
    //반환할 게 없는 VOID. addrestaurantFood 함수 만들거. 이건 음식 추가하는 함수
    public void addRestaurantFood(

//   {restaurantId} 와 동일한 이름을 갖는 파라미터를 추가.
            @PathVariable Long restaurantId,

            //fdto에서 리스트 갖고올거다.
            @RequestBody List<FoodRequestDto> requestDtoList
    ) {
        //그래서 서비스에 addrestaurantfood 실행시켜서 레스토랑 아이디랑 이 리스트 넣을거임.
        foodService.addRestaurantFood(restaurantId, requestDtoList);
    }


    //메뉴판 조회
    @GetMapping("/restaurant/{restaurantId}/foods")

    //레스토랑 아이디로 모든 레스토랑 푸드를 food 리스트에 담아서 서비스로 줘라.
    public List<Food> findAllRestaurantFoods(
            @PathVariable Long restaurantId
    ) {
        return foodService.findAllRestaurantFoods(restaurantId);
    }
}
