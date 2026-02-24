package com.app.ohmusecake.domain.order.dto.validator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.app.ohmusecake.domain.cake.entity.CakeCategory;
import com.app.ohmusecake.domain.cake.entity.CakeOption;
import com.app.ohmusecake.domain.cake.entity.CakeSize;
import com.app.ohmusecake.domain.order.dto.request.CreateOrderRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderValidator implements ConstraintValidator<OrderReqeustCheck, CreateOrderRequest> {

  @Override
  public boolean isValid(CreateOrderRequest request, ConstraintValidatorContext context) {
    if (request == null) return false;

    boolean valid = true;
    context.disableDefaultConstraintViolation();

    // 케이크 사이즈에 따른 레터링 검증
    valid &= validateLettering(request.getCakeSize(), request.getLetteringText(), context);

    // 케이크 종류별 사이즈 제한
    valid &= validateCakeSize(request.getCakeCategory(), request.getCakeSize(), context);

    // 케이크 종류별 맛 제한

    // 케이크 종류별 옵션 제한
    valid &= validateOptions(request.getCakeCategory(), request.getCakeOptions(), context);

    // 픽업 날짜 + 시간 제한
    valid &= validatePickupDateTime(request.getPickupDate(), request.getPickupTime(), context);

    return valid;
  }

  /// ** 유효성 검사 ** ///
  private boolean validateLettering(
      CakeSize cakeSize, String letteringText, ConstraintValidatorContext context) {

    if (cakeSize == null || letteringText == null) return true; // @NotNull에서 따로 처리

    int length = letteringText.trim().length();
    int max;
    if (cakeSize == CakeSize.MINI) {
      max = 13;
    } else {
      max = 20;
    }

    if (length <= max) {
      return true;
    }

    context.disableDefaultConstraintViolation();
    context
        .buildConstraintViolationWithTemplate(
            String.format(
                "%s 사이즈는 %d자 이하만 입력 가능합니다.", cakeSize == CakeSize.MINI ? "미니" : "해당", max))
        .addPropertyNode("letteringText")
        .addConstraintViolation();

    return false;
  }

  private boolean validateCakeSize(
      CakeCategory cakeCategory, CakeSize cakeSize, ConstraintValidatorContext context) {
    if (cakeCategory == null) return true; // @NotNull로 처리

    /// 쁘띠케이크 유효성 검사
    if (cakeCategory == CakeCategory.PETIT) {
      if (cakeSize != null) {
        addError(context, "cakeSize", "쁘띠케이크는 사이즈를 선택할 수 없습니다.");
        return false;
      }

      return true;
    }
    /// 컵케이크 사이즈 유효성 검사
    if (cakeCategory == CakeCategory.FLOWER_CUPCAKE) {
      if (cakeSize != CakeSize.SET_2 && cakeSize != CakeSize.SET_4) {
        addError(context, "cakeSize", "컵케이크는 2구와 4구만 선택 가능합니다");
        return false;
      }
      return true;
    } else {
      // 컵케이크가 아닌데 2구, 4구 선택한 경우
      if (cakeSize == CakeSize.SET_2 || cakeSize == CakeSize.SET_4) {
        addError(context, "cakeSize", "2구와 4구 사이즈는 컵케이크 선택 시에만 가능합니다");
        return false;
      }
    }

    /// 디자인/하트 사이즈 제한
    if (cakeCategory == CakeCategory.DESIGN || cakeCategory == CakeCategory.HEART) {
      if (cakeSize == CakeSize.TALL_MINI) {
        String categoryName = cakeCategory == CakeCategory.DESIGN ? "디자인" : "하트";
        addError(
            context, "cakeSize", String.format("%s 케이크는 높은 미니 사이즈를 선택할 수 없습니다.", categoryName));
        return false;
      }
      return true;
    }
    return true;
  }

  private boolean validateOptions(
      CakeCategory cakeCategory, List<CakeOption> cakeOptions, ConstraintValidatorContext context) {
    if (cakeOptions == null || cakeOptions.isEmpty()) return true;
    if (cakeCategory == null) return true; // @NotNull로 처리

    if (cakeCategory != CakeCategory.HEART) {
      addError(context, "cakeOptions", "하트 케이크 주문시에만 케이크 옵션을 선택할 수 있습니다.");
      return false;
    }
    return true;
  }

  private boolean validatePickupDateTime(
      LocalDate pickupDate, LocalTime pickupTime, ConstraintValidatorContext context) {
    if (pickupDate == null || pickupTime == null) return true; // @NotNull

    LocalDateTime pickupDateTime = LocalDateTime.of(pickupDate, pickupTime);

    if (pickupDateTime.isBefore(LocalDateTime.now(ZoneId.of("Asia/Seoul")))) {
      addError(context, "pickupDateTime", "픽업 날짜와 시간은 현재 이후여야 합니다");
      return false;
    }
    //// 영업시간 이외 픽업 불가
    DayOfWeek day = pickupDate.getDayOfWeek();
    LocalTime open, close;
    if (day == DayOfWeek.SATURDAY) {
      open = LocalTime.of(11, 0);
      close = LocalTime.of(18, 0);
    } else if (day == DayOfWeek.SUNDAY) {
      // 일요일은 무인픽업만 가능 → 대면 제한
      return true;
    } else {
      open = LocalTime.of(11, 0);
      close = LocalTime.of(19, 0);
    }

    if (pickupTime.isBefore(open) || pickupTime.isAfter(close)) {
      addError(context, "pickupTime", "해당 요일의 픽업 가능 시간은 " + open + " ~ " + close + " 입니다.");
      return false;
    }

    return true;
  }

  /// **** 중복코드 분리 **** ////
  private void addError(ConstraintValidatorContext context, String field, String message) {
    context.disableDefaultConstraintViolation();
    context
        .buildConstraintViolationWithTemplate(message)
        .addPropertyNode(field)
        .addConstraintViolation();
  }
}
