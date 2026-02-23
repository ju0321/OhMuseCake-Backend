package com.app.ohmusecake.domain.cake.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.ohmusecake.domain.cake.dto.response.DetailCakeResponse;
import com.app.ohmusecake.domain.cake.dto.response.SummaryCakeResponse;
import com.app.ohmusecake.domain.cake.entity.Cake;
import com.app.ohmusecake.domain.cake.exception.CakeErrorCode;
import com.app.ohmusecake.domain.cake.repository.CakeRepository;
import com.app.ohmusecake.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CakeService {

  private final CakeRepository cakeRepository;

  // summary page 조회 api (상품 list로 보기)
  @Transactional(readOnly = true)
  public List<SummaryCakeResponse> getCakeSummaries() {
    return cakeRepository.findByIsVisibleTrue().stream()
        .map(SummaryCakeResponse::from) // 또는 new CakeSummaryResponse(...)
        .toList();
  }

  // mainpage-best 상품 summary 조회 api (best 상품 list로 보기)
  @Transactional(readOnly = true)
  public List<SummaryCakeResponse> getBestCakeSummaries() {
    return cakeRepository.findByIsBestTrueAndIsVisibleTrue().stream()
        .map(SummaryCakeResponse::from)
        .toList();
  }

  // detailpage 조회 api
  @Transactional(readOnly = true)
  public DetailCakeResponse getCakeDetail(Long cakeId) {
    Cake cake =
        cakeRepository
            .findById(cakeId)
            .orElseThrow(
                () -> {
                  log.error("{}번 케이크를 찾을 수 없습니다.", cakeId);
                  return new CustomException(CakeErrorCode.CAKE_NOT_FOUND);
                });
    log.info("{}번 케이크를 성공적으로 조회했습니다.", cakeId);
    if (!cake.isVisible()) {
      throw new CustomException(CakeErrorCode.CAKE_NOT_FOUND);
    }
    return DetailCakeResponse.from(cake);
  }

  // delete는 나중에 - isVisible로 처리

}
