/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.ohmusecake.domain.order.dto.request.CreateOrderRequest;
import com.app.ohmusecake.domain.order.entity.Order;
import com.app.ohmusecake.domain.order.entity.OrderCake;
import com.app.ohmusecake.domain.order.repository.OrderCakeRepository;
import com.app.ohmusecake.domain.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderCakeRepository orderCakeRepository;

  // 주문서 생성
  @Transactional
  public Long createOrder(CreateOrderRequest request) {

    Order order =
        Order.builder()
            .customerName(request.getCustomerName())
            .phone(request.getPhone())
            .pickupDate(request.getPickupDate())
            .pickupTime(request.getPickupTime())
            .letteringText(request.getLetteringText())
            .requestNote(request.getRequestNote())
            .referenceImageUrl(request.getReferenceImageUrl())
            .build();

    orderRepository.save(order);

    // 2. HeartCakeOption → JSON (간단 버전)
    String heartCakeOptions = null;

    if (request.getHeartCakeOptions() != null && !request.getHeartCakeOptions().isEmpty()) {
      heartCakeOptions = request.getHeartCakeOptions().toString();
    }

    OrderCake orderCake =
        OrderCake.builder()
            .order(order) // FK 연결 핵심
            .cakeCategory(request.getCakeCategory())
            .cakeSize(request.getCakeSize())
            .cakeFlavor(request.getCakeFlavor())
            .heartCakeOptions(heartCakeOptions)
            .build();

    orderCakeRepository.save(orderCake);

    log.info("Order created. orderId={}", order.getId());
    return order.getId();
  }

  // 주문서 조회
  @Transactional(readOnly = true)
  public Order getOrder(Long orderId) {
    return orderRepository
        .findById(orderId)
        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
  }

  // 주문 목록 조회(로그인 없는 사용자 식별용)
  @Transactional(readOnly = true)
  public List<Order> getOrderByPhone(String phone) {
    return orderRepository.findByPhone(phone);
  }
}
