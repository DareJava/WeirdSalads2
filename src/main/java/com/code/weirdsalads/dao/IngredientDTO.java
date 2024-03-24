package com.code.weirdsalads.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientDTO {
    private Long id;
    private String name;
}
