/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.controller;

import com.app.ohmusecake.domain.order.dto.request.CreateOrderRequest;
import com.app.ohmusecake.domain.order.entity.Order;
import com.app.ohmusecake.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @Operation(summary="주문서 생성", description="사용자로부터 주문 요청 받아 주문서 생성")
  @PostMapping
  public Long createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
    return orderService.createOrder(createOrderRequest);
  }

  @GetMapping(value="/{order-id}")
  public Order getOrder(@PathVariable(value="order-id") Long orderId) {
    return orderService.getOrder(orderId);
  }

  @GetMapping
  public List<Order> getOrderByPhone(@RequestParam String phone) {
    return orderService.getOrderByPhone(phone);
  }
}
