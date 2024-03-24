package com.code.weirdsalads.dao;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class ReportResult {
    private BigDecimal totalCostOfAllDeliveries;
    private BigDecimal totalRevenueFromAllSales;
    private BigDecimal totalValueOfCurrentInventory;
    private BigDecimal costOfAllRecordedWaste;
}
