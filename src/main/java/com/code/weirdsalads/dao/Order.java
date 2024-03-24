package com.code.weirdsalads.dao;

import lombok.Data;

@Data
public class Order {
    private Long menuId;
    private Long restaurantId;
    private Long staffId;
}
