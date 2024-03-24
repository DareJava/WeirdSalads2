package com.code.weirdsalads.exception;

import com.code.weirdsalads.dao.MenuUnavailabilityReason;

public class MenuUnvailableException extends  RuntimeException {

    public MenuUnvailableException(String menu, MenuUnavailabilityReason reason) {
        super(String.format("Menu unavailable for: %s, reason: %s", menu, reason.getMessage()));
    }

    public MenuUnvailableException(String menu, String ingredientMissing, MenuUnavailabilityReason reason) {
        super(String.format("Menu unavailable for: %s, reason: %s, ingredient missing: %s", menu, reason.getMessage(), ingredientMissing));
    }
}
