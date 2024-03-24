package com.code.weirdsalads.service;

import com.code.weirdsalads.dao.DeliveryByType;
import com.code.weirdsalads.dao.IncomingDelivery;
import com.code.weirdsalads.dao.IngredientDTO;
import com.code.weirdsalads.model.Delivery;
import com.code.weirdsalads.model.DeliveryItem;
import com.code.weirdsalads.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Component
public class DeliveryService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DeliveryItemService deliveryItemService;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private InventoryService inventoryService;


    @Transactional
    public void processIncomingDelivery(IncomingDelivery delivery) {
        Long staffId = delivery.getReceivingStaffId();
        restaurantService.validateStaff(delivery.getRestaurantId(), staffId);

        Map<Long, Integer> ingredientCounts = new HashMap<>();

        List<DeliveryByType> deliveryIngredients = delivery.getIngredients();
        List<DeliveryItem> allIds = new ArrayList<>();

        // create a delivery
        Delivery deliveredBatch  = new Delivery();
        deliveredBatch.setDeliveryDate(Instant.now());
        deliveredBatch.setRestaurantId(delivery.getRestaurantId());
        deliveredBatch.setReceivedBy(delivery.getReceivingStaffId());
        deliveredBatch.setTotalCost(getAllDeliveryCost(delivery.getIngredients()));

        Delivery savedBatch = deliveryRepository.save(deliveredBatch);

        deliveryIngredients.forEach((deliveryIngredient) -> {
            DeliveryItem deliveryItem = new DeliveryItem();
            deliveryItem.setCount(deliveryIngredient.getCount());
            deliveryItem.setIngredientId(deliveryIngredient.getIngredientId());
            deliveryItem.setDeliveryId(savedBatch.getId());

            allIds.add(deliveryItem);
            ingredientCounts.put(deliveryIngredient.getIngredientId(), deliveryIngredient.getCount());
        });

        deliveryItemService.save(allIds);

        updateInventory(ingredientCounts, delivery.getRestaurantId(), staffId);
    }

    private BigDecimal getAllDeliveryCost(List<DeliveryByType> ingredients) {
        return ingredients.stream()
                .map(this::deliverySum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<IngredientDTO> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    public BigDecimal getTotalCostOfAllDeliveries(Long restaurantId) {
        List<Delivery> deliveries = deliveryRepository.findAllByDateFieldForCurrentMonth(restaurantId);
        return deliveries.stream().map(Delivery::getTotalCost).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void updateInventory(Map<Long, Integer> ingredientCounts, Long restaurantId, Long staffId) {
        inventoryService.update(ingredientCounts, restaurantId, staffId);
    }

    private BigDecimal deliverySum(DeliveryByType deliveryByType) {
        BigDecimal costPerUnit = deliveryByType.getCostPerUnit();
        int count = deliveryByType.getCount();
        return costPerUnit.multiply(BigDecimal.valueOf(count));
    }
}
