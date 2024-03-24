package com.code.weirdsalads.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="sta")
@Data
@EqualsAndHashCode(exclude = { "restaurant"}) // This,
@ToString(exclude = { "restaurant"}) // and this
public class Staff {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id", nullable=true)
    private Restaurant restaurant;

    public Staff() {

    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}