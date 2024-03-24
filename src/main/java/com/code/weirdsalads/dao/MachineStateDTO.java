package com.code.weirdsalads.dao;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MachineStateDTO {
    private boolean ready;
    private InitializerDTO info;
}
