/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.ohmusecake.domain.order.dto.request.CreateOrderRequest;
import com.app.ohmusecake.domain.order.dto.response.DetailOrderResponse;
import com.app.ohmusecake.domain.order.entity.HeartCakeOptions;
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
  public DetailOrderResponse getOrder(Long orderId) {
    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));

    OrderCake orderCake = orderCakeRepository.findByOrder(order);

    return toDetailOrderResponse(order, orderCake);
  }

  // 주문 목록 조회(로그인 없는 사용자 식별용)
  @Transactional(readOnly = true)
  public List<DetailOrderResponse> getOrderByPhone(String phone) {

    List<Order> orders = orderRepository.findByPhone(phone);

    return orders.stream()
        .map(
            order -> {
              OrderCake orderCake = orderCakeRepository.findByOrder(order);
              return toDetailOrderResponse(order, orderCake);
            })
        .toList();
  }

  private DetailOrderResponse toDetailOrderResponse(Order order, OrderCake orderCake) {

    return DetailOrderResponse.builder()
        .orderId(order.getId())
        .customerName(order.getCustomerName())
        .phone(order.getPhone())
        .pickupDate(order.getPickupDate())
        .pickupTime(order.getPickupTime())
        .cakeCategory(orderCake.getCakeCategory().getLabel())
        .cakeSize(orderCake.getCakeSize().getLabel())
        .cakeFlavor(orderCake.getCakeFlavor().getLabel())
        .heartCakeOptions(parseHeartCakeOptions(orderCake.getHeartCakeOptions()))
        .letteringText(order.getLetteringText())
        .requestNote(order.getRequestNote())
        .referenceImageUrl(order.getReferenceImageUrl())
        .build();
  }

  // 파싱함수
  private List<String> parseHeartCakeOptions(String options) {
    if (options == null || options.isBlank()) {
      return List.of();
    }

    return Arrays.stream(options.replace("[", "").replace("]", "").split(","))
        .map(String::trim)
        .map(opt -> HeartCakeOptions.valueOf(opt).getLabel())
        .toList();
  }
}
