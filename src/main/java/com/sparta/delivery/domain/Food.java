package com.sparta.delivery.domain;


import com.sparta.delivery.dto.FoodRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    //음식이 many. 식당이 One, 식당 아이디가 푸드 테이블에 들어가있으니까 외래키는 resId가 가진다.
    //조인컬럼은 외래키 지정 어노테이션
    @ManyToOne
    @JoinColumn(name = "restaurant_id",nullable = false)
    private Restaurant restaurant;


    //객체생성
    @Builder
    //앞에 컬럼지정해줬으니 객체 만들어줘야지. food에는 dto에서 이름이랑 가격 갖고오고, 조인컬럼한 레스토랑 넣어주자
    public Food(FoodRequestDto requestDto, Restaurant restaurant){
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurant = restaurant;
    }
}
