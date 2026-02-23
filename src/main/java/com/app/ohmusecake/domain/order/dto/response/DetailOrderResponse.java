package com.app.ohmusecake.domain.order.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "DetailOrderResponse: 주문서 상세보기 반환 응답 DTO")
@Builder
public class DetailOrderResponse {

  @Schema(description = "주문서 식별자", example = "1")
  private Long orderId;

  @Schema(description = "주문자 이름", example = "홍길동")
  private String customerName;

  @Schema(description = "주문자 폰번호", example = "010-1234-5678")
  private String phone;

  @Schema(description = "픽업 날짜", example = "2026-03-21")
  private LocalDate pickupDate;

  @Schema(description = "픽업 시간", example = "16:00")
  private LocalTime pickupTime;

  // cake 정보 label
  @Schema(description = "케이크 카테고리", example = "DESIGN")
  private String cakeCategory;

  @Schema(description = "케이크 사이즈", example = "SIZE_3")
  private String cakeSize;

  @Schema(description = "케이크 맛", example = "VANILA")
  private String cakeFlavor;

  @Schema(description = "케이크 옵션 (복수 선택)", example = "[\"생크림 색 변경\", \"젤리 추가\"]")
  private List<String> cakeOptions;

  @Schema(description = "레터링 글자", example = "생일축하해")
  private String letteringText;

  @Schema(description = "요청사항", example = "-")
  private String requestNote;

  @Schema(description = "참고 사진", example = "image.jpg")
  private String referenceImageUrl;

  @Schema(description = "추가상품 옵션 id 목록", example = "[1, 3, 5]")
  private List<Long> extraProductIds;

  @Schema(description = "주문 상태", example = "완료")
  private String orderStatus;
}
