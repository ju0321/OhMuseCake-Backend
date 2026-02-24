package com.app.ohmusecake.domain.extraProduct.entity;

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

@Entity
@Table(name = "extra_product")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtraProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "description")
  private String description;

  @Builder.Default
  @Column(name = "is_visible", nullable = false)
  private boolean visible = true;

  public void hide() {
    this.visible = false;
  }

  public void show() {
    this.visible = true;
  }
}
