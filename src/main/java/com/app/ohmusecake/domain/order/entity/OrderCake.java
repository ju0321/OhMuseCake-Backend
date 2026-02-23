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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.app.ohmusecake.domain.cake.entity.CakeCategory;
import com.app.ohmusecake.domain.cake.entity.CakeFlavor;
import com.app.ohmusecake.domain.cake.entity.CakeSize;

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

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false, unique = true)
  private Order order;

  @Enumerated(EnumType.STRING)
  @Column(name = "cake_category", nullable = false)
  private CakeCategory cakeCategory;

  @Enumerated(EnumType.STRING)
  @Column(name = "cake_size", nullable = false)
  private CakeSize cakeSize;

  @Enumerated(EnumType.STRING)
  @Column(name = "cake_flavor", nullable = false)
  private CakeFlavor cakeFlavor;

  // 케이크 옵션 변경 (복수 선택 → JSON 저장)
  @Column(name = "cake_options", columnDefinition = "TEXT")
  private String cakeOptions;
}
