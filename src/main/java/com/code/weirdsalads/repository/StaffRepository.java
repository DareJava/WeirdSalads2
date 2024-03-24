package com.code.weirdsalads.repository;

import com.code.weirdsalads.model.Restaurant;
import com.code.weirdsalads.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Set<Staff> getByRestaurantId(Long restaurantId);
}
