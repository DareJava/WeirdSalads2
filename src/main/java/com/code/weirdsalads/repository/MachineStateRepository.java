package com.code.weirdsalads.repository;

import com.code.weirdsalads.model.MachineState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MachineStateRepository extends JpaRepository<MachineState, Long> {
}
