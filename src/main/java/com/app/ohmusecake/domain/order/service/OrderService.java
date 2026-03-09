package com.app.ohmusecake.domain.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.ohmusecake.domain.cake.entity.CakeOption;
import com.app.ohmusecake.domain.order.dto.request.CreateOrderRequest;
import com.app.ohmusecake.domain.order.dto.response.DetailOrderResponse;
import com.app.ohmusecake.domain.order.entity.ExtraOption;
import com.app.ohmusecake.domain.order.entity.Order;
import com.app.ohmusecake.domain.order.entity.OrderCake;
import com.app.ohmusecake.domain.order.entity.OrderExtra;
import com.app.ohmusecake.domain.order.entity.OrderStatus;
import com.app.ohmusecake.domain.order.exception.OrderErrorCode;
import com.app.ohmusecake.domain.order.repository.OrderCakeRepository;
import com.app.ohmusecake.domain.order.repository.OrderExtraRepository;
import com.app.ohmusecake.domain.order.repository.OrderRepository;
import com.app.ohmusecake.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderCakeRepository orderCakeRepository;
  private final OrderExtraRepository orderExtraRepository;

  @Transactional
  public Long createOrder(CreateOrderRequest request) {

    Order order =
        Order.builder()
            .customerName(request.getCustomerName())
            .phone(request.getPhone())
            .pickupDate(request.getPickupDate())
            .pickupTime(request.getPickupTime())
            .letteringText(request.getLetteringText())
            .requestNote(request.getRequestNote())
            .referenceImageUrl(request.getReferenceImageUrl())
            .status(OrderStatus.REQUEST)
            .build();

    orderRepository.save(order);

    String cakeOptionsJson = null;
    if (request.getCakeOptions() != null && !request.getCakeOptions().isEmpty()) {
      cakeOptionsJson =
          request.getCakeOptions().stream()
              .map(CakeOption::name)
              .collect(Collectors.joining(",", "[", "]"));
    }

    OrderCake orderCake =
        OrderCake.builder()
            .order(order)
            .cakeCategory(request.getCakeCategory())
            .cakeSize(request.getCakeSize())
            .cakeFlavor(request.getCakeFlavor())
            .cakeOptions(cakeOptionsJson)
            .build();

    orderCakeRepository.save(orderCake);

    if (request.getExtraOptions() != null && !request.getExtraOptions().isEmpty()) {
      for (ExtraOption extraOption : request.getExtraOptions()) {
        orderExtraRepository.save(
            OrderExtra.builder().order(order).extraOption(extraOption).build());
      }
    }

    log.info("Order created. orderId={}", order.getId());
    return order.getId();
  }

  @Transactional(readOnly = true)
  public DetailOrderResponse getOrder(Long orderId) {

    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(
                () -> {
                  log.error("{}번 주문서를 찾을 수 없습니다.", orderId);
                  return new CustomException(OrderErrorCode.ORDER_NOT_FOUND);
                });

    OrderCake orderCake =
        orderCakeRepository
            .findByOrderId(orderId)
            .orElseThrow(
                () -> {
                  log.error("{}번 주문서에 대한 케이크 정보를 찾을 수 없습니다.", orderId);
                  return new CustomException(OrderErrorCode.ORDER_NOT_FOUND);
                });

    List<String> extraOptions =
        orderExtraRepository.findByOrderId(orderId).stream()
            .map(oe -> oe.getExtraOption().getLabel())
            .toList();

    log.info("{}번 주문서를 성공적으로 조회했습니다.", orderId);

    return toDetailOrderResponse(order, orderCake, extraOptions);
  }

  @Transactional(readOnly = true)
  public List<DetailOrderResponse> getOrderByPhone(String phone) {

    List<Order> orders = orderRepository.findByPhone(phone);

    if (orders.isEmpty()) {
      log.error("전화번호 {}에 대한 주문 내역이 없습니다.", phone);
      throw new CustomException(OrderErrorCode.ORDER_PHONE_MISMATCH);
    }

    log.info("전화번호 {}로 {}건의 주문을 조회했습니다.", phone, orders.size());

    return orders.stream()
        .map(this::buildResponse)
        .toList();
  }

  @Transactional(readOnly = true)
  public List<DetailOrderResponse> getOrderList() {

    return orderRepository.findAll().stream()
        .map(this::buildResponse)
        .toList();
  }

  private DetailOrderResponse toDetailOrderResponse(
      Order order, OrderCake orderCake, List<String> extraOptions) {

    return DetailOrderResponse.builder()
        .orderId(order.getId())
        .customerName(order.getCustomerName())
        .phone(order.getPhone())
        .pickupDate(order.getPickupDate())
        .pickupTime(order.getPickupTime())
        .cakeCategory(orderCake.getCakeCategory().getLabel())
        .cakeSize(orderCake.getCakeSize().getLabel())
        .cakeFlavor(orderCake.getCakeFlavor().getLabel())
        .cakeOptions(parseCakeOptions(orderCake.getCakeOptions()))
        .extraOptions(extraOptions)
        .letteringText(order.getLetteringText())
        .requestNote(order.getRequestNote())
        .referenceImageUrl(order.getReferenceImageUrl())
        .orderStatus(order.getStatus().getLabel())
        .build();
  }

  private DetailOrderResponse buildResponse(Order order) {
    OrderCake orderCake = orderCakeRepository.findByOrderId(order.getId())
        .orElseThrow(() -> new CustomException(OrderErrorCode.ORDER_NOT_FOUND));
    List<String> extraOptions = orderExtraRepository.findByOrderId(order.getId()).stream()
        .map(oe -> oe.getExtraOption().getLabel()).toList();
    return toDetailOrderResponse(order, orderCake, extraOptions);
  }


  @Transactional
  public void changeOrderStatus(Long orderId, OrderStatus status) {

    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new CustomException(OrderErrorCode.ORDER_NOT_FOUND));

    order.changeStatus(status);
  }

  private List<String> parseCakeOptions(String options) {
    if (options == null || options.isBlank()) {
      return List.of();
    }
    return java.util.Arrays.stream(options.replace("[", "").replace("]", "").split(","))
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .map(opt -> CakeOption.valueOf(opt).getLabel())
        .toList();
  }
}
