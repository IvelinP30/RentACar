package com.RentACar.RentACar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private Set<User> clients;

    public City(){
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getClients() {
        return clients;
    }

    public void setClients(Set<User> clients) {
        this.clients = clients;
    }
}