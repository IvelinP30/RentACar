package com.RentACar.RentACar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fuels")
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "fuel")
    private Set<Car> car;

    public Fuel() {
    }

    public Fuel(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Car> getCar() {
        return car;
    }

    public void setCar(Set<Car> car) {
        this.car = car;
    }
}