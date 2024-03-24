package com.code.weirdsalads.dao;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InitializerDTO {
    private Long restaurantId;
    private String restaurantName;
    private List<StaffDTO> staffs;
}
