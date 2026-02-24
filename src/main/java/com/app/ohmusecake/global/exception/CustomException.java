/*
 * Copyright (c) SKU K-IO-SK
 */
package com.app.ohmusecake.global.exception;

import com.app.ohmusecake.global.exception.model.BaseErrorCode;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private final BaseErrorCode errorCode;

  public CustomException(BaseErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
