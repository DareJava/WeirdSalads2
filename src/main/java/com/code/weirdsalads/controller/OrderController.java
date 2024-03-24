package com.code.weirdsalads.controller;

import com.code.weirdsalads.dao.Order;
import com.code.weirdsalads.dao.OrderResult;
import com.code.weirdsalads.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController extends BaseController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping("/orders")
    public ResponseEntity accept(@RequestBody Order order) {
        Long orderId = customerOrderService.enqueueOrder(order);
        return ResponseEntity.ok(OrderResult.builder().orderId(orderId).build());
    }
}
