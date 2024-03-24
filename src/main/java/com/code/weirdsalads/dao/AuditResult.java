package com.code.weirdsalads.dao;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class AuditResult {
    private String message;
    private String perpetratorName;
    private String actionDate;
    private String behaviour;
}
