package com.code.weirdsalads.repository;

import com.code.weirdsalads.model.CustomerOrder;
import com.code.weirdsalads.model.DeliveryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryItemRepository extends JpaRepository<DeliveryItem, Long>  {
}
