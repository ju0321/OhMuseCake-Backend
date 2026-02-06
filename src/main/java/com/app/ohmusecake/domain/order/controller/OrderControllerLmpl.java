/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.app.ohmusecake.domain.order.controller;

import org.springframework.web.bind.annotation.RestController;

import com.app.ohmusecake.domain.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerLmpl implements OrderController {

  private final OrderService orderService;
}
