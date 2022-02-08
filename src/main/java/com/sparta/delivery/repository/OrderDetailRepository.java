package com.sparta.delivery.repository;

import com.sparta.delivery.domain.OrderDetail;
import com.sparta.delivery.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findOrderDetailsByOrders(Orders orders);
}
