/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "DetailOrderResponse: 주문서 상세보기 반환 응답 DTO")
@Builder
public class DetailOrderResponse {

  @Schema(description = "주문서 식별자", example = "1")
  private Long orderId;

  @Schema(description = "", example = "")
  private String customerName;

  @Schema(description = "", example = "")
  private String phone;

  @Schema(description = "", example = "")
  private LocalDate pickupDate;

  @Schema(description = "", example = "")
  private LocalTime pickupTime;

  @Schema(description = "", example = "")
  private String letteringText;

  @Schema(description = "", example = "")
  private String requestNote;

  @Schema(description = "", example = "")
  private String referenceImageUrl;

  // cake 관련 필드는 나중에 추가
}
