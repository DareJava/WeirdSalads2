package com.code.weirdsalads.repository;


import com.code.weirdsalads.model.Inventory;
import com.code.weirdsalads.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>  {

    List<Menu> getByRestaurantId(Long restaurantId);

}
