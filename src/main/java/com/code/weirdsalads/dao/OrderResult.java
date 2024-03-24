package com.code.weirdsalads.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResult {
    private Long orderId;
}
