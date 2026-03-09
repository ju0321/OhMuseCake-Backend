/*
 * Copyright (c) SKU K-IO-SK
 */
package com.app.ohmusecake.global.exception.model;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

  String getCode();

  String getMessage();

  HttpStatus getStatus();
}
