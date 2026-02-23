package com.app.ohmusecake.domain.order.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.app.ohmusecake.domain.cake.entity.CakeCategory;
import com.app.ohmusecake.domain.cake.entity.CakeFlavor;
import com.app.ohmusecake.domain.cake.entity.CakeOption;
import com.app.ohmusecake.domain.cake.entity.CakeSize;
import com.app.ohmusecake.domain.order.entity.ExtraOption;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(title = "CreateOrderRequest: 주문서 생성 요청 DTO")
public class CreateOrderRequest {

  @Schema(description = "주문자 이름", example = "홍길동")
  private String customerName;

  @Schema(description = "주문자 핸드폰 번호", example = "010-1234-5678")
  private String phone;

  @Schema(description = "픽업 날짜", example = "2025-12-04")
  private LocalDate pickupDate;

  @Schema(description = "픽업 시간", example = "15:00")
  private LocalTime pickupTime;

  @Schema(description = "케이크 종류", example = "DESIGN")
  private CakeCategory cakeCategory;

  @Schema(description = "케이크 옵션 변경 (복수 선택)", example = "[\"CREAM_COLOR_CHANGE\", \"JELLY_ADD\"]")
  private List<CakeOption> cakeOptions;

  @Schema(description = "케이크 사이즈", example = "SIZE_2")
  private CakeSize cakeSize;

  @Schema(description = "케이크 시트 맛", example = "VANILLA")
  private CakeFlavor cakeFlavor;

  @Schema(description = "레터링 문구", example = "생일축하해")
  private String letteringText;

  @Schema(description = "추가 옵션 목록", example = "[\"ALPHABET_CHOCOLATE\", \"CLEAR_BOX\"]")
  private List<ExtraOption> extraOptions;

  @Schema(description = "디자인 디테일 / 요청사항", example = "꽃 많이 넣어주세요")
  private String requestNote;

  @Schema(description = "참고 이미지 URL", example = "https://example.com/image.jpg")
  private String referenceImageUrl;
}
