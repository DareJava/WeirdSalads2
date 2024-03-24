package com.code.weirdsalads.repository;

import com.code.weirdsalads.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {


    @Query("SELECT e FROM CustomerOrder e WHERE MONTH(e.createdAt) = MONTH(CURRENT_DATE) AND YEAR(e.createdAt) = YEAR(CURRENT_DATE) AND e.restaurantId = restaurantId")
    List<CustomerOrder> findAllByDateFieldForCurrentMonth(Long restaurantId);
}