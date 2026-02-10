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
@Schema(title = "CakeDetailResponse : 케이크 상세설명 응답 DTO")
public class DetailCakeResponse {
  @Schema(description = "케이크 id", example = "1")
  private Long cakeId;

  @Schema(description = "케이크 종류", example = "HEART")
  private String cakeCategory;

  @Schema(description = "케이크 사이즈", example = "SIZE_2")
  private String cakeSize;

  @Schema(description = "케이크 맛", example = "VANILA")
  private String cakeFlavor;

  @Schema(description = "케이크 가격", example = "19,000")
  private int price;

  @Schema(description = "케이크 이미지 url", example = "image.jpg")
  private String imageUrl;

  @Schema(description = "케이크 설명", example = "-")
  private String description;

  public static DetailCakeResponse from(Cake cake) {
    return new DetailCakeResponse(
        cake.getId(),
        cake.getCakeCategory().name(),
        cake.getCakeSize().name(),
        cake.getCakeFlavor().name(),
        cake.getPrice(),
        cake.getImageUrl(),
        cake.getDescription());
  }
}
