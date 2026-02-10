/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.ohmusecake.domain.cake.entity.HeartCakeOptions;
import com.app.ohmusecake.domain.order.dto.request.CreateOrderRequest;
import com.app.ohmusecake.domain.order.dto.response.DetailOrderResponse;
import com.app.ohmusecake.domain.order.entity.Order;
import com.app.ohmusecake.domain.order.entity.OrderCake;
import com.app.ohmusecake.domain.order.entity.OrderStatus;
import com.app.ohmusecake.domain.order.exception.OrderErrorCode;
import com.app.ohmusecake.domain.order.repository.OrderCakeRepository;
import com.app.ohmusecake.domain.order.repository.OrderRepository;
import com.app.ohmusecake.global.exception.CustomException;

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
            .status(OrderStatus.REQUEST)
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

  @Transactional(readOnly = true)
  public DetailOrderResponse getOrder(Long orderId) {

    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(
                () -> {
                  log.error("{}번 주문서를 찾을 수 없습니다.", orderId);
                  return new CustomException(OrderErrorCode.ORDER_NOT_FOUND);
                });

    OrderCake orderCake =
        orderCakeRepository
            .findByOrderId(orderId)
            .orElseThrow(
                () -> {
                  log.error("{}번 주문서에 대한 케이크 정보를 찾을 수 없습니다.", orderId);
                  return new CustomException(OrderErrorCode.ORDER_NOT_FOUND);
                });

    log.info("{}번 주문서를 성공적으로 조회했습니다.", orderId);

    return toDetailOrderResponse(order, orderCake);
  }

  @Transactional(readOnly = true)
  public List<DetailOrderResponse> getOrderByPhone(String phone) {

    List<Order> orders = orderRepository.findByPhone(phone);

    if (orders.isEmpty()) {
      log.error("전화번호 {}에 대한 주문 내역이 없습니다.", phone);
      throw new CustomException(OrderErrorCode.ORDER_NOT_FOUND);
    }

    log.info("전화번호 {}로 {}건의 주문을 조회했습니다.", phone, orders.size());

    return orders.stream()
        .map(
            order -> {
              OrderCake orderCake =
                  orderCakeRepository
                      .findByOrderId(order.getId())
                      .orElseThrow(
                          () -> {
                            log.error("{}번 주문서에 대한 케이크 정보를 찾을 수 없습니다.", order.getId());
                            return new CustomException(OrderErrorCode.ORDER_NOT_FOUND);
                          });

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
        .orderStatus(order.getStatus().getLabel())
        .build();
  }

  @Transactional
  public void changeOrderStatus(Long orderId, OrderStatus status) {

    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));

    order.changeStatus(status);
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
