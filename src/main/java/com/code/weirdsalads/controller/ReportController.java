package com.code.weirdsalads.controller;

import com.code.weirdsalads.dao.AuditResult;
import com.code.weirdsalads.dao.Order;
import com.code.weirdsalads.dao.ReportResult;
import com.code.weirdsalads.service.InventoryEventService;
import com.code.weirdsalads.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController extends BaseController {

    @Autowired
    private ReportingService reportingService;

    @Autowired
    private InventoryEventService inventoryService;

    @GetMapping("/reports/numbers")
    public ResponseEntity<ReportResult> churnNumbers(@RequestParam Long restaurantId) {
        ReportResult result = reportingService.crunchNumbers(restaurantId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/reports/inventory")
    public ResponseEntity<List<AuditResult>> getInventoryAudits(@RequestParam Long restaurantId) {
        List<AuditResult> result = inventoryService.churnData(restaurantId);
        return ResponseEntity.ok(result);
    }
}
