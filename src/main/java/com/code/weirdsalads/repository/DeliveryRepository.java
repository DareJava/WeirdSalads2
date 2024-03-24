package com.code.weirdsalads.repository;


import com.code.weirdsalads.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("SELECT e FROM Delivery e WHERE MONTH(e.deliveryDate) = MONTH(CURRENT_DATE) AND YEAR(e.deliveryDate) = YEAR(CURRENT_DATE) AND e.restaurantId = restaurantId")
    List<Delivery> findAllByDateFieldForCurrentMonth(Long restaurantId);
}