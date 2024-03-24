package com.code.weirdsalads.controller;

import com.code.weirdsalads.dao.MenuDTO;
import com.code.weirdsalads.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RestaurantController extends BaseController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants/{restaurantId}/menu")
    public ResponseEntity<List<MenuDTO>> get(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.getMenu(restaurantId));
    }
}
