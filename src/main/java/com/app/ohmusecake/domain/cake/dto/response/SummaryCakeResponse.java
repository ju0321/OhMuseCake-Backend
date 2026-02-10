/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.cake.dto.response;

import com.app.ohmusecake.domain.cake.entity.Cake;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "CakeSummaryResponse : 메인페이지 베스트 상품 응답 DTO")
public class SummaryCakeResponse {

  @Schema(description = "케이크 id", example = "1")
  private Long cakeId;

  @Schema(description = "케이크 종류", example = "HEART")
  private String cakeCategory;

  @Schema(description = "케이크 가격", example = "19,000")
  private int price;

  @Schema(description = "케이크 이미지 url", example = "image.jpg")
  private String imageUrl;

  public static SummaryCakeResponse from(Cake cake) {
    return new SummaryCakeResponse(
        cake.getId(), cake.getCakeCategory().name(), cake.getPrice(), cake.getImageUrl());
  }
}
