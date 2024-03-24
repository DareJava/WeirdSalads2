package com.code.weirdsalads.controller;

import com.code.weirdsalads.dao.IngredientDTO;
import com.code.weirdsalads.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController extends BaseController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/ingredients")
    public ResponseEntity<List<IngredientDTO>> get() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }
}
