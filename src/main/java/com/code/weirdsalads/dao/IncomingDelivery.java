package com.code.weirdsalads.dao;

import lombok.Data;

import java.util.List;

@Data
public class IncomingDelivery {
    private List<DeliveryByType> ingredients;
    private Long restaurantId;
    private Long receivingStaffId;
}
