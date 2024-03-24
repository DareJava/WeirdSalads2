package com.code.weirdsalads.service;

import com.code.weirdsalads.model.DeliveryItem;
import com.code.weirdsalads.repository.DeliveryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryItemService {

    @Autowired
    private DeliveryItemRepository deliveryItemRepository;

    public void save(List<DeliveryItem> deliveryItemList) {
        deliveryItemRepository.saveAll(deliveryItemList);
    }
}
