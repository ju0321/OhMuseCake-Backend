package com.app.ohmusecake.domain.extraProduct.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ohmusecake.domain.extraProduct.dto.response.ExtraProductResponse;
import com.app.ohmusecake.domain.extraProduct.service.ExtraProductService;
import com.app.ohmusecake.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/extra_products")
public class ExtraProductController {

  private final ExtraProductService extraProductService;

  @GetMapping
  @Operation(summary = "추가상품 목록 조회", description = "주문서 작성 시 선택 가능한 추가상품 목록을 반환합니다.")
  public BaseResponse<List<ExtraProductResponse>> getExtraProducts() {
    return BaseResponse.success(extraProductService.getVisibleExtraProducts());
  }
}
