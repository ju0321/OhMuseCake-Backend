package com.app.ohmusecake.domain.order.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "orders")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private OrderCake orderCake;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderExtra> orderExtras = new ArrayList<>();

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

  @Column(name = "request_note", columnDefinition = "TEXT")
  private String requestNote;

  @Column(name = "reference_image_url")
  private String referenceImageUrl;

  @Enumerated(EnumType.STRING)
  @Column(name = "order_status", nullable = false)
  private OrderStatus status;

  public void changeStatus(OrderStatus status) {
    this.status = status;
  }
}
