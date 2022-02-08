package com.sparta.delivery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
private int quantity;

    @Column
private int price;

    @ManyToOne(cascade = CascadeType.ALL)
private Food food;

    @ManyToOne
private Orders orders;

    @Builder
public OrderDetail(String name, int quantity, int price, Food food){
    this.name = name;
    this.quantity = quantity;
    this.price = price;
    this.food = food;
    }
}




