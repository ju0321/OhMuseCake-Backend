/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateOrderRequest {
  private String customerName;
  private String phone;

  private LocalDate pickupDate;
  private LocalTime pickupTime;

  private String letteringText;
  private String requestNote;
  private String referenceImageUrl;

  // cake 관련 필드는 나중에 추가
}
