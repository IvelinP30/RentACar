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

    @Column(name = "brand")
    @NotNull
    private String brand;

    @Column(name = "model")
    @NotNull
    private String model;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private Set<UserCar> carUsers;

    public Car(String brand, String model, Category category, Set<UserCar> carUsers) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.carUsers = carUsers;
    }

    public Car() {
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
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

    public Set<UserCar> getCarUsers() {
        return carUsers;
    }

    public void setCarUsers(Set<UserCar> carUsers) {
        this.carUsers = carUsers;
    }
}