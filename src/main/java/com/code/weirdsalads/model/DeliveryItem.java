package com.code.weirdsalads.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="delivery_item")
public class DeliveryItem {
    private Long ingredientId;

    private Long deliveryId;

    private Integer count;

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }
}
