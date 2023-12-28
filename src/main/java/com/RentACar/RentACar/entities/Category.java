package com.RentACar.RentACar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Car> car;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
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