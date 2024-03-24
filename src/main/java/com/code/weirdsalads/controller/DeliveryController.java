package com.code.weirdsalads.controller;

import com.code.weirdsalads.dao.DeliveryByType;
import com.code.weirdsalads.dao.IncomingDelivery;
import com.code.weirdsalads.model.Ingredient;
import com.code.weirdsalads.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeliveryController extends BaseController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/deliveries/record")
    public ResponseEntity record(@RequestBody IncomingDelivery delivery) {
        deliveryService.processIncomingDelivery(delivery);
        return ResponseEntity.ok("Saved");
    }
}
