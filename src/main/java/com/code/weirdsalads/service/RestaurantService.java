package com.code.weirdsalads.service;

import com.code.weirdsalads.dao.MenuDTO;
import com.code.weirdsalads.model.Restaurant;
import com.code.weirdsalads.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuService menuService;

    public void validateStaff(Long restaurantId, Long staffId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("This Restaurant doesnt exist"));
        restaurant.getStaffs().stream()
                .filter((staffer) -> Objects.equals(staffer.getId(), staffId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Staff: " + staffId + "is not a staff of this restaurant"));
    }

    public Restaurant getById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found for "+ restaurantId));
        return restaurant;
    }

    public List<MenuDTO> getMenu(Long restaurantId) {
        return menuService.getAllMenuForRestaurant(restaurantId);
    }

}
