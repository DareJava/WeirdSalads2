package com.code.weirdsalads.service;

import com.code.weirdsalads.dao.InitializerDTO;
import com.code.weirdsalads.dao.StaffDTO;
import com.code.weirdsalads.model.*;
import com.code.weirdsalads.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Component
public class InitializerService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MachineStateRepository machineStateRepository;

    @Transactional
    public InitializerDTO add(String name, String address) {
        Restaurant restaurant = createRestaurant(name, address);
        List<StaffDTO> staff = createStaff(restaurant);
        Set<Ingredient> ingredients = createIngredients();
        createMenu(restaurant.getId(), ingredients);
        saveMachineState(restaurant.getId());
        return InitializerDTO.builder().restaurantId(restaurant.getId()).restaurantName(name).staffs(staff).build();
    }

    private void saveMachineState(Long restaurantId) {
        MachineState machineState = new MachineState();
        machineState.setRestaurantId(restaurantId);
        machineStateRepository.save(machineState);
    }

    private Restaurant createRestaurant(String name, String address) {
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setName(name);
        return restaurantRepository.saveAndFlush(restaurant);
    }

    private List<StaffDTO> createStaff(Restaurant restaurant) {
        Faker faker = new Faker();
        Set<Staff> staffs = new HashSet<>();
        for(int i = 0; i < 5; i++) {
            Staff staff = new Staff();
            staff.setFirstName(faker.name().firstName());
            staff.setLastName(faker.name().lastName());
            staff.setLastName(faker.name().lastName());
            staff.setRestaurant(restaurant);
            staffs.add(staff);
        }
        return staffRepository.saveAll(new HashSet<>(staffs)).stream()
                .map((staff -> StaffDTO.builder().id(staff.getId()).name(staff.getFirstName()).build()))
                .toList();
    }

    private Set<Ingredient> createIngredients() {
        Set<Ingredient> allIngredients = new HashSet<>();
        Faker f = new Faker();
        for(int i = 0; i < 5; i++) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(f.food().ingredient());
            ingredient.setCostPerUnit(BigDecimal.valueOf(5));
            ingredientRepository.save(ingredient);
            allIngredients.add(ingredient);
        }
        return allIngredients;
    }

    private void createMenu(Long restaurantId, Set<Ingredient> ingredients) {
        Faker faker = new Faker();

        for(int i = 0; i < 5; i++) {
            Menu menu  = new Menu();
            int firstIngredient = new Random().nextInt(ingredients.size()) + 1;
            int secondIngredient = new Random().nextInt(ingredients.size()) + 1;
            menu.setRestaurantId(restaurantId);
            menu.setIngredientsCsv(makeCsvFromInts(firstIngredient, secondIngredient));
            menu.setNameInFull(faker.food().dish());
            menu.setCost(BigDecimal.valueOf(new Random().nextInt(300)));

            menuRepository.save(menu);
        }
    }

    private String makeCsvFromInts(int first, int second) {
        return String.format("%s,%s", first, second);
    }
}
