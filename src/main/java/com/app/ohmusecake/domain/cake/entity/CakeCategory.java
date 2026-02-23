package com.app.ohmusecake.domain.cake.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CakeCategory {
  DESIGN("디자인 케이크"),
  HEART("하트 케이크"),
  FLOWER("생화 케이크"),
  FLOWER_JELLY("생화 젤리 케이크"),
  FLOWER_CUPCAKE("생화 컵케이크"),
  PETIT("쁘띠 케이크");

  private final String label;
};
