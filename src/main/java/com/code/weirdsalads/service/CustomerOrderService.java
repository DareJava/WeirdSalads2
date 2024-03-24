package com.code.weirdsalads.service;

import com.code.weirdsalads.dao.MenuDTO;
import com.code.weirdsalads.dao.Order;
import com.code.weirdsalads.model.CustomerOrder;
import com.code.weirdsalads.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Component
public class CustomerOrderService {
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;


    @Transactional
    public Long enqueueOrder(Order order) {
        MenuDTO menuDTO = menuService.getMenu(order.getMenuId());

        inventoryService.removeIngredients(order.getRestaurantId(), menuDTO.getName(), menuDTO.getIngredientsId(), order.getStaffId());

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCost(menuDTO.getCost());
        customerOrder.setRestaurantId(order.getRestaurantId());
        customerOrder.setMenuId(menuDTO.getMenuId());
        customerOrderRepository.save(customerOrder);

        return customerOrder.getId();
    }

    public BigDecimal getTotalRevenueFromAllSales(Long restaurantId) {
      List<CustomerOrder> customerOrders = customerOrderRepository.findAllByDateFieldForCurrentMonth(restaurantId);
      return customerOrders.stream().map(CustomerOrder::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
