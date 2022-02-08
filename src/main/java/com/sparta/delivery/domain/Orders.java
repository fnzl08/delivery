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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String restaurantName;

    @Column
    private Long totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders id")
    private List<OrderDetail> foods;

    @Builder
    public Orders(String restaurantName, Long totalPrice, List<OrderDetail> orderDetails){
        this.restaurantName = restaurantName;
        this.totalPrice = totalPrice;
        this.foods = orderDetails;
    }
}
