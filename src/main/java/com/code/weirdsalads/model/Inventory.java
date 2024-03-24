package com.code.weirdsalads.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Inventory {

    private Long ingredientId;

    private Integer count;

    private Long restaurantId;

    private Long staffId;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Instant createdAt;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
