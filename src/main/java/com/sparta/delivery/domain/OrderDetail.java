package com.sparta.delivery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//주문 상세에 넣을 것들 - 주문상세 id, 음식이름, 음식수량, 음식 개당 가격,
@Getter
@Entity
@NoArgsConstructor
public class OrderDetail {

    //주문상세 - id, 음식이름, 양, 가격, orderdetail안에 food 여러개, orders에 orderdetail들어있다.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private int quantity;

    @Column
    private int price;

    //주문상세에는 한종류 음식의 이름과 양과 가격,  1종류 음식에 전체 디비로 보면 주문 여러개 들어올 수 있음.
    @ManyToOne(cascade = CascadeType.ALL)
    private Food food;

    @Builder
    public OrderDetail(String name, int quantity, int price, Food food) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.food = food;
    }
}




