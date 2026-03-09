package com.app.ohmusecake.domain.cake.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CakeSize {
  MINI("미니(2~3인"),
  TALL_MINI("높은 미니"),
  SIZE_1("1호"),
  SIZE_2("2호"),
  SIZE_3("3호"),
  SET_2("2구"),
  SET_4("4구");

  private final String label;
}
