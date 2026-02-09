/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ohmusecake.domain.order.entity.Order;
import com.app.ohmusecake.domain.order.entity.OrderCake;

public interface OrderCakeRepository extends JpaRepository<OrderCake, Long> {

  OrderCake findByOrder(Order order);
}
