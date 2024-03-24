package com.code.weirdsalads.service;

import com.code.weirdsalads.dao.StaffDTO;
import com.code.weirdsalads.model.Staff;
import com.code.weirdsalads.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public Staff getStaff(Long staffId) {
        return staffRepository.findById(staffId).orElseThrow(() -> new RuntimeException("Staff not existent"));
    }

    public List<StaffDTO> getStaffByRestaurant(Long restaurantId) {
        return staffRepository.getByRestaurantId(restaurantId).stream().map((staff -> StaffDTO.builder().id(staff.getId()).name(staff.getFirstName()).build())).collect(Collectors.toList());
    }
}
