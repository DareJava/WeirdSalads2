package com.code.weirdsalads.service;

import com.code.weirdsalads.dao.MenuDTO;
import com.code.weirdsalads.model.Menu;
import com.code.weirdsalads.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuDTO> getAllMenuForRestaurant(Long restaurantId) {
        return menuRepository.getByRestaurantId(restaurantId).stream()
                .map((menu) -> MenuDTO.builder()
                        .name(menu.getNameInFull())
                        .menuId(menu.getId())
                        .restaurantId(restaurantId)
                        .build())
                .collect(Collectors.toList());
    }

    public MenuDTO getMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new RuntimeException("Menu not available"));
        List<Long> ingredients = Arrays.stream(menu.getIngredientsCsv().split(",")).map((ingredient) -> Long.parseLong(ingredient)).collect(Collectors.toList());
        return MenuDTO.builder()
                .cost(menu.getCost())
                .name(menu.getNameInFull())
                .menuId(menu.getId())
                .ingredientsId(ingredients)
                .build();
    }
}
