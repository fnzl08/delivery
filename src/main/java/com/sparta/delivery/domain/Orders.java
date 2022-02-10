package com.sparta.delivery.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Orders {

    //식당이름, orderdetail 컬럼으로 끌어올거고. totalprice

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int totalPrice;


    //컬럼안에 orderdetail 아이디 들어가야하니까 오더에만 단방향. 근데 order가 one 쪽인 상황(detail이 여러개)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id") //오더 아이디 외래키주기
    private List<OrderDetail> foods; //디테일내용리스트 컬럼 foods  저장할게. 이걸로 꺼내 써

    @Builder
    public Orders(String restaurantName,  List<OrderDetail> orderDetails, int totalPrice){
        this.restaurantName = restaurantName;
        this.foods = orderDetails;
        this.totalPrice = totalPrice;
    }
}
