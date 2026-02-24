package com.app.ohmusecake.domain.extraProduct.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.ohmusecake.domain.extraProduct.dto.response.ExtraProductResponse;
import com.app.ohmusecake.domain.extraProduct.entity.ExtraProduct;
import com.app.ohmusecake.domain.extraProduct.exception.ExtraProductErrorCode;
import com.app.ohmusecake.domain.extraProduct.repository.ExtraProductRepository;
import com.app.ohmusecake.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExtraProductService {

  private final ExtraProductRepository extraProductRepository;

  // 주문서용 추가상품 전체 조회 (visible 상태만)
  @Transactional(readOnly = true)
  public List<ExtraProductResponse> getVisibleExtraProducts() {
    return extraProductRepository.findByVisibleTrue().stream()
        .map(ExtraProductResponse::from)
        .toList();
  }

  // detailpage 조회 api
  @Transactional(readOnly = true)
  public ExtraProductResponse getExtraProductDetail(Long id) {
    ExtraProduct extraProduct =
        extraProductRepository
            .findById(id)
            .orElseThrow(
                () -> {
                  log.error("{}번 추가상품을 찾을 수 없습니다.", id);
                  return new CustomException(ExtraProductErrorCode.EXTRA_NOT_FOUND);
                });
    if (!extraProduct.isVisible()) {
      throw new CustomException(ExtraProductErrorCode.EXTRA_NOT_FOUND);
    }
    log.info("{}번 상품을 성공적으로 조회했습니다.", id);
    return ExtraProductResponse.from(extraProduct);
  }
}
