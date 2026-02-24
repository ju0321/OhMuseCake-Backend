package com.app.ohmusecake.domain.order.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExtraOption {
  HANDWRITING_GARLAND("손글씨 가랜더"),
  HANDWRITING_CANDLE("손글씨 초 추가"),
  CLEAR_BOX("투명 상자");

  private final String label;
}
