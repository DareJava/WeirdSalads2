package com.code.weirdsalads.util;

import com.code.weirdsalads.dao.AuditResult;
import com.code.weirdsalads.dao.InventoryEventType;
import com.code.weirdsalads.model.InventoryEvent;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class InventoryEventUtil {

    public AuditResult convert(InventoryEvent event) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(event.getCreatedAt(), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String behaviour = event.getEventType() == InventoryEventType.ACCEPTING_NEW_INVENTORY ? "New Delivery" : "Customer order";

        return AuditResult.builder()
                .actionDate(localDateTime.format(formatter))
                .perpetratorName(event.getStaffName())
                .behaviour(behaviour)
                .message(event.toString())
                .build();
    }
}
