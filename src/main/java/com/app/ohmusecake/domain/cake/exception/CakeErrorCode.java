/*
 * Copyright (c) SKU K-IO-SK
 */
package com.app.ohmusecake.domain.cake.exception;

import org.springframework.http.HttpStatus;

import com.app.ohmusecake.global.exception.model.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CakeErrorCode implements BaseErrorCode {
  CAKE_NOT_FOUND("CAKE_4041", "존재하지 않는 케이크입니다.", HttpStatus.NOT_FOUND);

  private String code;
  private String message;
  private HttpStatus status;
}
