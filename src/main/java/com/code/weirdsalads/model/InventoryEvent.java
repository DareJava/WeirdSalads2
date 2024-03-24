package com.code.weirdsalads.model;

import com.code.weirdsalads.dao.InventoryEventType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class InventoryEvent {

    private Long id;

    private String staffName;

    private InventoryEventType eventType;

    private Integer countFrom;

    private Integer countTo;

    private Long restaurantId;

    private String ingredientName;

    private Instant createdAt = Instant.now();

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public String toString() {
        String action = this.getEventType() == InventoryEventType.ACCEPTING_NEW_INVENTORY ? "added" : "removed";
        return String.format("Ingredient (%s) was %s, count moved from %d to %d", this.getIngredientName(), action, this.getCountFrom(), this.getCountTo());
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
}
