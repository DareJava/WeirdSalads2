package com.code.weirdsalads.service;

import com.code.weirdsalads.dao.IngredientDTO;
import com.code.weirdsalads.model.Ingredient;
import com.code.weirdsalads.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients(List<Long> ingredientIds) {
        return ingredientRepository.findAllById(ingredientIds);
    }

    public List<IngredientDTO> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map((ingredient)-> IngredientDTO.builder().name(ingredient.getName()).id(ingredient.getId()).build())
                .collect(Collectors.toList());
    }

    public Ingredient getIngredient(Long ingredientId) {
        return ingredientRepository.findById(ingredientId).orElseThrow(() -> new RuntimeException("Ingredient" + ingredientId + "unavailable"));
    }
}
