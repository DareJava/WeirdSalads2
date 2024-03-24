package com.code.weirdsalads.controller;

import com.code.weirdsalads.dao.InitRequest;
import com.code.weirdsalads.dao.InitializerDTO;
import com.code.weirdsalads.dao.MachineStateDTO;
import com.code.weirdsalads.service.MachineStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MachineStateController extends BaseController {

    @Autowired
    private MachineStateService machineStateService;

    @GetMapping("/status")
    public ResponseEntity<MachineStateDTO> check() {
        return ResponseEntity.ok(machineStateService.initializerInfo());
    }

    @PostMapping("/initialize")
    public ResponseEntity<InitializerDTO> initialize(@RequestBody InitRequest initRequest) {
        return ResponseEntity.ok(machineStateService.initializeLocation(initRequest));
    }
}
