package com.code.weirdsalads.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffDTO {
    private String name;
    private Long id;
}
