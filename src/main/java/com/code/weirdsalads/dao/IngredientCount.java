package com.code.weirdsalads.dao;

import com.code.weirdsalads.model.Ingredient;
import lombok.Data;

@Data
public class IngredientCount {
    private Ingredient ingredient;
    private int count;
}
