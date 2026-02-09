package com.app.ohmusecake.domain.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "order_cake")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCake {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY) // OrderCake만 order 참조
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  // enum으로 만들기
  @Enumerated(EnumType.STRING)
  @Column(name = "cake_category", nullable = false)
  private CakeCategory cakeCategory;

  @Enumerated(EnumType.STRING)
  @Column(name = "cake_size", nullable = false)
  private CakeSize cakeSize;

  @Enumerated(EnumType.STRING)
  @Column(name = "cake_flavor", nullable = false)
  private CakeFlavor cakeFlavor;

  //하트 케이크 옵션(복수선택 -> JSON)
  //Enumerated(EnumType.STRING)
  @Column(name = "heart_cake_option", columnDefinition = "TEXT")
  private HeartCakeOption heartCakeOption;

}
