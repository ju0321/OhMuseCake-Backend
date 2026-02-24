package com.app.ohmusecake.domain.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ohmusecake.domain.order.entity.OrderExtra;

public interface OrderExtraRepository extends JpaRepository<OrderExtra, Long> {

  List<OrderExtra> findByOrderId(Long orderId);
}
