package com.app.ohmusecake.domain.order.dto.validator;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE) // orderRequest 객체에 적용하므로 Type지정
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrderValidator.class)
public @interface OrderReqeustCheck {
  String message() default "주문 요청이 유효하지 않습니다.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
