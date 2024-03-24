package com.code.weirdsalads.repository;

import com.code.weirdsalads.model.Inventory;
import com.code.weirdsalads.model.InventoryEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface InventoryEventRepository extends JpaRepository<InventoryEvent, Long> {
    List<InventoryEvent> getAllByRestaurantIdOrderByCreatedAtDesc(Long restaurantId);
}