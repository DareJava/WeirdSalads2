package com.code.weirdsalads.service;

import com.code.weirdsalads.dao.InventoryEventRequest;
import com.code.weirdsalads.dao.InventoryEventType;
import com.code.weirdsalads.dao.MenuUnavailabilityReason;
import com.code.weirdsalads.exception.MenuUnvailableException;
import com.code.weirdsalads.model.Ingredient;
import com.code.weirdsalads.model.Inventory;
import com.code.weirdsalads.model.Staff;
import com.code.weirdsalads.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private InventoryEventService inventoryEventService;

    @Transactional
    public void update(Map<Long, Integer> counts, Long restaurantId, Long staffId) {
        Set<Long> ingredientIds = counts.keySet();
        Staff staff = staffService.getStaff(staffId);

        for(Long ingredientId: ingredientIds) {
            Inventory allByIngredientIdAndRestaurantId = inventoryRepository.getByIngredientIdAndRestaurantId(ingredientId, restaurantId);
            Integer countForInventory = counts.get(ingredientId);
            Ingredient ingredient = ingredientService.getIngredient(ingredientId);
            if (allByIngredientIdAndRestaurantId != null) {
                Integer oldCount = allByIngredientIdAndRestaurantId.getCount();
                Integer newCount = oldCount + countForInventory;
                allByIngredientIdAndRestaurantId.setCount(newCount);
                inventoryRepository.save(allByIngredientIdAndRestaurantId);
                sendInventoryEvent(restaurantId, staff, oldCount, newCount, ingredient, InventoryEventType.ACCEPTING_NEW_INVENTORY);
            } else {
                Inventory inventory = new Inventory();
                inventory.setCount(countForInventory);
                inventory.setCreatedAt(Instant.now());
                inventory.setRestaurantId(restaurantId);
                inventory.setIngredientId(ingredientId);
                inventory.setStaffId(staffId);
                inventoryRepository.save(inventory);
                sendInventoryEvent(restaurantId, staff, 0, countForInventory, ingredient, InventoryEventType.ACCEPTING_NEW_INVENTORY);
            }
        }
    }

    @Transactional
    public void removeIngredients(Long restaurantId, String name, List<Long> ingredientIds, Long staffId) {
        List<Inventory> inventories = inventoryRepository.findAllById(ingredientIds);
        inventories.forEach((inv) -> {
            Ingredient ingredient = ingredientService.getIngredient(inv.getIngredientId());
            Integer currentCount = inv.getCount();
            if (currentCount <= 0) {
                throw new MenuUnvailableException(name, ingredient.getName(), MenuUnavailabilityReason.INVENTORY_INSUFFICIENT);
            }
            Staff staff = staffService.getStaff(staffId);
            Integer newCount = inv.getCount() - 1;
            inv.setCount(newCount);
            inv.setStaffId(staffId);
            inventoryRepository.save(inv);
            sendInventoryEvent(restaurantId, staff, currentCount, newCount, ingredient, InventoryEventType.ORDER_INDUCED_REDUCTION);
        });
    }

    public BigDecimal getTotalValueOfInventory(Long restaurantId) {
        List<Inventory> inventories = inventoryRepository.getAllByRestaurantId(restaurantId);
        return inventories.stream()
                .map((inventory) -> getCostOfIngredient(inventory.getIngredientId(), inventory.getCount()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getCostOfIngredient(Long ingredientId, int count) {
        Ingredient ingredient = ingredientService.getIngredient(ingredientId);
        return BigDecimal.valueOf(count).multiply(ingredient.getCostPerUnit());
    }

    private void sendInventoryEvent(Long restaurantId, Staff staff, Integer countFrom, Integer countTo,
                                    Ingredient ingredient, InventoryEventType type) {
        InventoryEventRequest eventRequest = buildInventoryAuditEvent(restaurantId, staff, countFrom, countTo, ingredient, type);
        inventoryEventService.saveEvent(eventRequest);
    }

    private InventoryEventRequest buildInventoryAuditEvent(Long restaurantId, Staff staff, Integer countFrom, Integer countTo,
                                          Ingredient ingredient, InventoryEventType type) {
        return new InventoryEventRequest(restaurantId, staff, countFrom, countTo, ingredient, type);
    }
}
