package com.sparta.delivery.repository;

import com.sparta.delivery.domain.OrderDetail;
import com.sparta.delivery.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//주문 조회할 때 집어넣었던 오더디테일을 orders라고 할거임
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findOrderDetailsByOrders(Orders orders);
}
