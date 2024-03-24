package com.code.weirdsalads.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
public class CustomerOrder {
    private Long id;
    private Long menuId;
    private Long restaurantId;
    private BigDecimal cost;
    private Instant createdAt = Instant.now();

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
}
