package com.app.ohmusecake.domain.order.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExtraOption {
  ALPHABET_CHOCOLATE("알파벳 초콜릿 추가"),
  HANDWRITING_CANDLE("손글씨 초 추가"),
  CLEAR_BOX("투명 상자");

  private final String label;
}
