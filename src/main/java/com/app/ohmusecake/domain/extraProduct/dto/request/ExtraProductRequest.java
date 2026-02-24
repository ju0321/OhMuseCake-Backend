package com.app.ohmusecake.domain.extraProduct.dto.request;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(title = "ExtraProductRequest: 추가상품 요청 DTO")
public class ExtraProductRequest {

  @NotBlank
  @Schema(description = "추가상품 이름", example = "투명 상자")
  private String productName;

  @Schema(description = "상품 설명", example = "-")
  private String description;
}
