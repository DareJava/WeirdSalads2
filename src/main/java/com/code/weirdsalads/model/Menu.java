package com.code.weirdsalads.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Table(name="menu")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private BigDecimal cost;

    private String nameInFull;

    private String ingredientsCsv;

    private Long restaurantId;

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getNameInFull() {
        return nameInFull;
    }

    public void setNameInFull(String nameInFull) {
        this.nameInFull = nameInFull;
    }
}
