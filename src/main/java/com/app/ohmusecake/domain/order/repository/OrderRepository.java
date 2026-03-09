package com.app.ohmusecake.domain.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ohmusecake.domain.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByPhone(String phone);
}
