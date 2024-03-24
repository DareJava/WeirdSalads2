package com.code.weirdsalads.dao;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeliveryByType {
   private Integer count;
   private Long ingredientId;
   private BigDecimal costPerUnit;
}
