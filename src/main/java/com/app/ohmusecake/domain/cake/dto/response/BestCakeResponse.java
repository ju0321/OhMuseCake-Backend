package com.app.ohmusecake.domain.cake.dto.response;

import com.app.ohmusecake.domain.cake.entity.Cake;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "베스트 상품 응답 DTO")
public class BestCakeResponse {
  @Schema(description = "케이크 id", example = "1")
  private Long cakeId;

  @Schema(description = "케이크 종류", example = "HEART")
  private String cakeCategory;

  @Schema(description = "케이크 이미지 url", example = "image.jpg")
  private String imageUrl;

  @Schema(description = "케이크 설명", example = "-")
  private String description;

  @Schema(description = "베스트 케이크 여부", example = "-")
  private boolean isBest = false;

  public static BestCakeResponse from(Cake cake) {
    return BestCakeResponse.builder()
        .cakeId(cake.getId())
        .cakeCategory(cake.getCakeCategory().name())
        .imageUrl(cake.getImageUrl())
        .description(cake.getDescription())
        .isBest(cake.isBest())
        .build();
  }

}
