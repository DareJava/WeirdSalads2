package com.code.weirdsalads.service;

import com.code.weirdsalads.dao.AuditResult;
import com.code.weirdsalads.dao.InventoryEventRequest;
import com.code.weirdsalads.model.InventoryEvent;
import com.code.weirdsalads.repository.InventoryEventRepository;
import com.code.weirdsalads.util.InventoryEventUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryEventService {

    @Autowired
    private InventoryEventRepository inventoryEventRepository;

    public List<AuditResult> churnData(Long restaurantId) {
        List<InventoryEvent> inventoryEvents = inventoryEventRepository.getAllByRestaurantIdOrderByCreatedAtDesc(restaurantId);
        return inventoryEvents.stream().map(this::convert).toList();
    }

    public void saveEvent(InventoryEventRequest inventoryEventRequest) {
        InventoryEvent event = new InventoryEvent();
        event.setEventType(inventoryEventRequest.type());
        event.setRestaurantId(inventoryEventRequest.restaurantId());
        event.setStaffName(inventoryEventRequest.staff().getFirstName());
        event.setIngredientName(inventoryEventRequest.ingredient().getName());
        event.setCountFrom(inventoryEventRequest.countFrom());
        event.setCountTo(inventoryEventRequest.countTo());
        inventoryEventRepository.save(event);
    }

    private AuditResult convert(InventoryEvent event) {
        return InventoryEventUtil.convert(event);
    }
}
