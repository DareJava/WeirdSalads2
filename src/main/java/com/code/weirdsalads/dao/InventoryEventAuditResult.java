package com.code.weirdsalads.dao;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class InventoryEventAuditResult {
    private InventoryEventType eventType;
    private String from, to;
    private Instant occuredAt;
    private Long perpetrator;
}
