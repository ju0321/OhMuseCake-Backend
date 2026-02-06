/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.entity;

import jakarta.persistence.Entity;
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
  // @Enumerated(EnumType.STRING)
  // private CakeCategory cake_category;
  // private CakeSize cake_size;
  // private CakeFlavor cake_flavor;
  // private CakeOption cake_option;
  // private ExtraOption extra_option;

}
