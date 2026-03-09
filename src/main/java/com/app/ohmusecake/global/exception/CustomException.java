package com.app.ohmusecake.global.exception;

import com.app.ohmusecake.global.exception.model.BaseErrorCode;

import lombok.Getter;

@Getter
// 런타임 도중 사용자 정의 예외 발생시키도록 RuntimeException 상속
public class CustomException extends RuntimeException {
  // ㄴ> RuntimeException은 Unchecked 예외를 처리한다. 따라서 메서드 시그니처에 throws를 강제하지 않는다.
  // 따라서, 해당 클래스를 상속받으면 메소드에 throws를 남발하지 않아도 되서 API가 깔끔해진다.
  // 서비스/도메인 계층에서 throw new CustomException(errorCode)로 예외를 던질 수 있다. (컨트롤러까지 전파됨)
  // 전역 예외 처리기 @RestControllerAdvice에서 일관된 json 응답 만들기 가능함

  private final BaseErrorCode errorCode;

  // 해당 예외에 대한 예외 정보들을 담게 하기 위해 BaseErrorCode 클래스도 멤버 변수로 선언

  public CustomException(BaseErrorCode errorCode) {
    super(errorCode.getMessage()); // RuntimeException의 내부 message 필드를 채워주기 위함
    this.errorCode = errorCode;
  }
}
