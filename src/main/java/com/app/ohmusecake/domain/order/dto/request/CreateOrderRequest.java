/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.app.ohmusecake.domain.order.entity.CakeCategory;
import com.app.ohmusecake.domain.order.entity.CakeFlavor;
import com.app.ohmusecake.domain.order.entity.CakeSize;
import com.app.ohmusecake.domain.order.entity.HeartCakeOptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
// @Builder
@Schema(title = "CreatedOrderRequest: 주문서 생성 요청 DTO")
public class CreateOrderRequest {
  @Schema(description = "주문자이름", example = "홍길동")
  private String customerName;

  @Schema(description = "주문자 핸드폰 번호", example = "010-1234-5678")
  private String phone;

  @Schema(description = "픽업 날짜", example = "2025-12-04")
  private LocalDate pickupDate;

  @Schema(description = "픽업 시간", example = "15:00")
  private LocalTime pickupTime;

  @Schema(description = "케이크 종류", example = "DESIGN")
  private CakeCategory cakeCategory;

  @Schema(description = "케이크 사이즈", example = "SIZE_2")
  private CakeSize cakeSize;

  @Schema(description = "케이크 시트 맛", example = "VANILA")
  private CakeFlavor cakeFlavor;

  // 복수 선택 → List
  @Schema(description = "하트케이크 옵션(복수선택)", example = "CREAM_COLOR_CHANGE")
  private List<HeartCakeOptions> heartCakeOptions;

  @Schema(description = "레터링 글자", example = "생일축하해")
  private String letteringText;

  @Schema(description = "요청 메모", example = "-")
  private String requestNote;

  @Schema(description = "참고 이미지", example = "image.jpg")
  private String referenceImageUrl;
}
