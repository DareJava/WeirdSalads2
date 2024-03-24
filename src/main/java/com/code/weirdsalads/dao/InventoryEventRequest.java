package com.code.weirdsalads.dao;


import com.code.weirdsalads.model.Ingredient;
import com.code.weirdsalads.model.Staff;

public record InventoryEventRequest(Long restaurantId, Staff staff, Integer countFrom, Integer countTo,
                                    Ingredient ingredient, InventoryEventType type) {
}