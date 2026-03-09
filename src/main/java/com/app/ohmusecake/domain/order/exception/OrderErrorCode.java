package com.app.ohmusecake.domain.order.exception;

import org.springframework.http.HttpStatus;

import com.app.ohmusecake.global.exception.model.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderErrorCode implements BaseErrorCode {
  ORDER_NOT_FOUND("ORDER_4041", "존재하지 않는 주문입니다.", HttpStatus.NOT_FOUND),
  ORDER_PHONE_MISMATCH("ORDER_4031", "주문자 전화번호가 일치하지 않습니다.", HttpStatus.FORBIDDEN);

  private String code;
  private String message;
  private HttpStatus status;
}
