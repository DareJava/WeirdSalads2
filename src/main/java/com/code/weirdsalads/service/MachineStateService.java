package com.code.weirdsalads.service;

import com.code.weirdsalads.dao.InitRequest;
import com.code.weirdsalads.dao.InitializerDTO;
import com.code.weirdsalads.dao.MachineStateDTO;
import com.code.weirdsalads.dao.StaffDTO;
import com.code.weirdsalads.model.MachineState;
import com.code.weirdsalads.model.Restaurant;
import com.code.weirdsalads.repository.MachineStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MachineStateService {

    @Autowired
    private MachineStateRepository machineStateRepository;

    @Autowired
    private InitializerService initializerService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private StaffService staffService;

    public MachineStateDTO initializerInfo() {
        MachineStateDTO.MachineStateDTOBuilder builder = MachineStateDTO.builder();

        if (!machineStateRepository.findAll().isEmpty()) {
            builder.ready(true).info(getLocationInfo());
        } else {
            builder.ready(false);
        }
        return builder.build();
    }

    public InitializerDTO initializeLocation(InitRequest request) {
       return initializerService.add(request.getRestaurantName(),request.getAddress());
    }

    public InitializerDTO getLocationInfo() {
        MachineState machineState = machineStateRepository.findAll().get(0);
        Restaurant restaurant = restaurantService.getById(machineState.getRestaurantId());
        List<StaffDTO> staffers = staffService.getStaffByRestaurant(restaurant.getId());
        return InitializerDTO.builder().restaurantId(restaurant.getId()).restaurantName(restaurant.getName()).staffs(staffers).build();
    }
}
