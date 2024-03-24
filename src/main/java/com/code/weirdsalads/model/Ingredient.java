package com.code.weirdsalads.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="ingredient")
@Data
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal costPerUnit;

//    @ManyToOne
//    @JoinColumn(name="delivery_id", nullable=false)
//    private Delivery delivery;

//    @ManyToOne
//    @JoinColumn(name = "menu_item_id")
//    private MenuItem menuItem;
}
