package com.app.ohmusecake.domain.extraProduct.dto.response;

import com.app.ohmusecake.domain.extraProduct.entity.ExtraProduct;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(title = "ExtraProductResponse: 추가상품 응답 dto")
public class ExtraProductResponse {

  @Schema(description = "추가상품 id", example = "1")
  private Long id;

  @Schema(description = "추가상품 이름", example = "투명 상자")
  private String productName;

  @Schema(description = "추가상품 설명", example = "-")
  private String description;

  @Schema(description = "현재 상태", example = "true")
  private boolean visible;

  public static ExtraProductResponse from(ExtraProduct extraProduct) {
    return ExtraProductResponse.builder()
        .id(extraProduct.getId())
        .productName(extraProduct.getProductName())
        .description(extraProduct.getDescription())
        .visible(extraProduct.isVisible())
        .build();
  }
}
