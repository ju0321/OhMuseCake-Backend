package com.app.ohmusecake.domain.cake.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cake")
@Builder
public class Cake {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "cake_category", nullable = false)
  private CakeCategory cakeCategory;

  @Enumerated(EnumType.STRING)
  @Column(name = "cake_size", nullable = false)
  private CakeSize cakeSize;

  @Enumerated(EnumType.STRING)
  @Column(name = "cake_flavor", nullable = false)
  private CakeFlavor cakeFlavor;

  @Column(name = "price", nullable = false)
  private int price;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name="description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "is_best", nullable = false)
  @Builder.Default
  private boolean isBest = false;

  @Column(name = "is_visible", nullable = false)
  @Builder.Default
  private boolean isVisible = true;   //품절, 시즌종료, 임시 비공개

}
