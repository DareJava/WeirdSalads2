package com.code.weirdsalads.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name="delivery")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long receivedBy;

    private Long restaurantId;

    private Instant deliveryDate = Instant.now();

    private BigDecimal totalCost;

//    @OneToMany(mappedBy="delivery")
//    private Set<DeliveryItem> deliveryItems;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}