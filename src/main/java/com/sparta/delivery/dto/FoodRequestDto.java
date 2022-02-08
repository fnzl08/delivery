package com.sparta.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

//이 dto에는 음식 이름이랑 가격을 잠시 넣어줄거야. 이거 서비스로 들고갈거임 -> 푸드서비스에는 레스토랑 아이디, 음식이름, 음식 가격이 들어가게 되겠다.
public class FoodRequestDto {
    private String name;
    private int price;
    }
