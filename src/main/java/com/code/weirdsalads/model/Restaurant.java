package com.code.weirdsalads.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name="restaurant")
@Data
@EqualsAndHashCode(exclude = { "staffs"}) // This,
@ToString(exclude = { "staffs"}) // and this
public class Restaurant {

    @OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Staff> staffs;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String address;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}