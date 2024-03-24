package com.code.weirdsalads.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuUnavailabilityReason {
    INVENTORY_INSUFFICIENT("One of the ingredients missing"),
    KITCHEN_CLOSED("Kitchen doesnt accept extras");

    private String message;
}
