/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.cake.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ohmusecake.domain.cake.dto.response.DetailCakeResponse;
import com.app.ohmusecake.domain.cake.dto.response.SummaryCakeResponse;
import com.app.ohmusecake.domain.cake.service.CakeService;
import com.app.ohmusecake.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cakes")
@Tag(name = "케이크", description = "케이크 관련 API")
public class CakeController {

  private final CakeService cakeService;

  @Operation(summary = "메인화면 베스트 상품 리스트", description = "메인화면에서 베스트 상품 이미지, summary 반환 API")
  @GetMapping(value = "/best")
  public ResponseEntity<BaseResponse<List<SummaryCakeResponse>>> getBestCakeSummaries() {
    return ResponseEntity.status(200)
        .body(
            BaseResponse.success(200, "메인화면 베스트 상품 리스트 반환 성공", cakeService.getBestCakeSummaries()));
  }

  @Operation(summary = "케이크 전체 상품 반환 화면", description = "전체 상품 이미지, summary 반환 API")
  @GetMapping
  public ResponseEntity<BaseResponse<List<SummaryCakeResponse>>> getCakeSummaries() {
    return ResponseEntity.status(200)
        .body(BaseResponse.success(200, "전체 상품 화면 리스트 반환 성공", cakeService.getCakeSummaries()));
  }

  @Operation(summary = "상품 상세보기 화면", description = "상품 상세 정보 제공 API")
  @GetMapping(value = "/{cake-id}")
  public ResponseEntity<BaseResponse<DetailCakeResponse>> getDetailCake(
      @PathVariable(value = "cake-id") Long cakeId) {
    return ResponseEntity.status(200)
        .body(BaseResponse.success(200, "케이크 상세보기 화면 반환 성공", cakeService.getCakeDetail(cakeId)));
  }
}
