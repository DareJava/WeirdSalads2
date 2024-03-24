package com.code.weirdsalads.repository;

import com.code.weirdsalads.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory getByIngredientIdAndRestaurantId(Long ingredientId, Long restaurantId);

    List<Inventory> getAllByIngredientIdAndRestaurantId(Long ingredientId, Long restaurantId);

    List<Inventory> getAllByRestaurantId(Long restaurantId);
}
