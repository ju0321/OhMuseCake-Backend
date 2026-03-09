package com.app.ohmusecake.global.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.app.ohmusecake.global.exception.model.BaseErrorCode;
import com.app.ohmusecake.global.response.BaseResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 전역 예외 처리 클래스입니다.
 *
 * <p>Spring Controller에서 발생하는 예외를 처리하고, 클라이언트에게 일관된 형태의 {@link BaseResponse}를 반환합니다.
 *
 * <p>처리 범위:
 *
 * <ul>
 *   <li>커스텀 예외 (CustomException)
 *   <li>유효성 검증 실패 (MethodArgumentNotValidException)
 *   <li>정적 리소스 미존재 (NoResourceFoundException)
 *   <li>기타 예상치 못한 예외 (Exception)
 * </ul>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * CustomException 발생 시 처리합니다.
   *
   * @param ex 발생한 {@link CustomException}
   * @return {@link ResponseEntity} 형태의 {@link BaseResponse} 에러 응답
   */
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<BaseResponse<Object>> handleCustomException(CustomException ex) {
    BaseErrorCode errorCode = ex.getErrorCode();
    log.warn("CustomException 발생: {} - {}", errorCode.getCode(), errorCode.getMessage());
    return ResponseEntity.status(errorCode.getStatus())
        .body(BaseResponse.error(errorCode.getStatus().value(), errorCode.getMessage()));
  }

  /**
   * MethodArgumentNotValidException 발생 시 처리합니다.
   *
   * @param ex 발생한 {@link MethodArgumentNotValidException}
   * @return {@link ResponseEntity} 형태의 {@link BaseResponse} 에러 응답
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<BaseResponse<Object>> handleValidationException(
      MethodArgumentNotValidException ex) {
    String errorMessages =
        ex.getBindingResult().getFieldErrors().stream()
            .map(e -> String.format("[%s] %s", e.getField(), e.getDefaultMessage()))
            .collect(Collectors.joining(" / "));
    log.warn("Validation 오류 발생: {}", errorMessages);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(BaseResponse.error(400, errorMessages));
  }

  /**
   * NoResourceFoundException 발생 시 처리합니다.
   *
   * @param ex 발생한 {@link NoResourceFoundException}
   * @return {@link ResponseEntity} 형태의 {@link BaseResponse} 에러 응답
   */
  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<BaseResponse<Object>> handleNoResourceFound(NoResourceFoundException ex) {
    log.debug("정적 리소스 없음: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(BaseResponse.error(404, "리소스를 찾을 수 없습니다."));
  }

  /**
   * 그 외 예상치 못한 예외 발생 시 처리합니다.
   *
   * @param ex 발생한 {@link Exception}
   * @return {@link ResponseEntity} 형태의 {@link BaseResponse} 에러 응답
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseResponse<Object>> handleException(Exception ex) {
    log.error("Server 오류 발생", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(BaseResponse.error(500, "예상치 못한 서버 오류가 발생했습니다."));
  }
}
