package com.code.weirdsalads.dao;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class MenuDTO {
    private BigDecimal cost;
    private String name;
    private Long menuId;
    private Long restaurantId;
    private List<Long> ingredientsId;
}
