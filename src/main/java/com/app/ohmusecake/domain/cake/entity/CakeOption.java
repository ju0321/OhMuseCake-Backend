package com.app.ohmusecake.domain.cake.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CakeOption {
  NONE("없음"),
  CREAM_COLOR_CHANGE("생크림 색 변경"),
  CREAM_COLOR_CHANGE_DARK("생크림 색 변경 - 짙은 색상"),
  JELLY_ADD("젤리 추가"),
  FLOWER_ADD("생화 추가");

  private final String label;
}
