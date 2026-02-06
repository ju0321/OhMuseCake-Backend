/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "orders")
@Entity
@Getter
// @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "customer_name", nullable = false)
  private String customerName;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "pickup_date", nullable = false)
  private LocalDate pickupDate;

  @Column(name = "pickup_time", nullable = false)
  private LocalTime pickupTime;

  @Column(name = "lettering_text", columnDefinition = "TEXT")
  private String letteringText;

  @Column(name = "request_note")
  private String requestNote;

  @Column(name = "referenceImage")
  private String referenceImageUrl;

  // 이것도 enum으로
  // private OrderStatus status;   //Order, Cancel ,,,

  /*
  public void updateOrder(UpdateOrderRequest updateOrderReqeust, OrderCategory orderCategory...){
  }

  public void changeStatus(OrderStatus status) {
    this.status = status;
  }

  public void updatePickupInfo(LocalDate pickupDate, LocalTime pickupTime) {
    this.pickupDate = pickupDate;
    this.pickupTime = pickupTime;
  }

  public void updateRequest(String letteringText, String requestNote) {
    this.letteringText = letteringText;
    this.requestNote = requestNote;
  }
   */
}
