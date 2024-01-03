package com.RentACar.RentACar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import java.util.Set;


@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "brand")
    @NotNull
    private Brand  brand;

    @Column(name = "model")
    @NotNull
    private String model;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private Fuel fuel;

    @OneToMany(mappedBy = "car")
    private Set<UserCar> carUsers;

    public Car(Brand brand, String model, Category category, Fuel fuel, Set<UserCar> carUsers) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.fuel = fuel;
        this.carUsers = carUsers;
    }

    public Car() {
    }

    public long getId() {
        return id;
    }

    public Brand  getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }


    public Set<UserCar> getCarUsers() {
        return carUsers;
    }

    public void setCarUsers(Set<UserCar> carUsers) {
        this.carUsers = carUsers;
    }
}