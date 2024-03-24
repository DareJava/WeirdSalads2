package com.code.weirdsalads.service;

import com.code.weirdsalads.dao.ReportResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportingService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private CustomerOrderService customerOrderService;

    public ReportResult crunchNumbers(Long restaurantId) {
        return ReportResult.builder()
                .totalCostOfAllDeliveries(deliveryService.getTotalCostOfAllDeliveries(restaurantId))
                .totalValueOfCurrentInventory(inventoryService.getTotalValueOfInventory(restaurantId))
                .totalRevenueFromAllSales(customerOrderService.getTotalRevenueFromAllSales(restaurantId))
                .build();
    }
}
