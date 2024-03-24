package com.code.weirdsalads.repository;

import com.code.weirdsalads.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
