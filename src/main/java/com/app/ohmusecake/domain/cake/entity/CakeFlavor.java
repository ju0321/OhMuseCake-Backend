package com.app.ohmusecake.domain.cake.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CakeFlavor {
  VANILLA("바닐라"),
  VANILLA_CRISPY("바닐라 + 벨기에 크리스피볼"),
  CHOCOLATE("진한 초코"),
  CARAMEL_CRUNCH("카라멜 크런치");

  private final String label;
}
