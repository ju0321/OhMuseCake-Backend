/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
  REQUEST("주문 접수"),
  CONFIRMED("주문 확정"),
  DONE("제작 완료"),
  CANCELLED("주문 취소");

  private final String label;
}
