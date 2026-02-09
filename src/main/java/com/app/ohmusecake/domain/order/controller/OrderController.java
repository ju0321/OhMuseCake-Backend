/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ohmusecake.domain.order.dto.request.CreateOrderRequest;
import com.app.ohmusecake.domain.order.dto.response.DetailOrderResponse;
import com.app.ohmusecake.domain.order.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order", description = "주문 API")
public class OrderController {

  private final OrderService orderService;

  @Operation(summary = "주문서 생성", description = "사용자로부터 주문 요청 받아 주문서 생성")
  @PostMapping
  public Long createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
    return orderService.createOrder(createOrderRequest);
  }

  @Operation(summary = "주문 단건 조회", description = "주문 ID로 주문 조회.")
  @GetMapping(value = "/{order-id}")
  public DetailOrderResponse getOrder(@PathVariable(value = "order-id") Long orderId) {
    return orderService.getOrder(orderId);
  }

  @Operation(summary = "전화번호 주문 조회", description = "로그인 없이 전화번호로 주문 조회.")
  @GetMapping
  public List<DetailOrderResponse> getOrderByPhone(@RequestParam String phone) {
    return orderService.getOrderByPhone(phone);
  }
}
