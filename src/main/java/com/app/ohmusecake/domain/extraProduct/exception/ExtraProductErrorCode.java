package com.app.ohmusecake.domain.extraProduct.exception;

import org.springframework.http.HttpStatus;

import com.app.ohmusecake.global.exception.model.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExtraProductErrorCode implements BaseErrorCode {
  EXTRA_NOT_FOUND("Extra_4041", "존재하지 않는 케이크입니다.", HttpStatus.NOT_FOUND);

  private String code;
  private String message;
  private HttpStatus status;
}
