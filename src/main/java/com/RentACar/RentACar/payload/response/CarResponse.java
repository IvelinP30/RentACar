package com.RentACar.RentACar.payload.response;

import com.RentACar.RentACar.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CarResponse {
    private String Brand;
    private String Model;
    private String registrationNum;
    private List<UserResponse> Users = new ArrayList<>();

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public List<UserResponse> getUsers() {
        return Users;
    }

    public void setUsers(List<UserResponse> users) {
        Users = users;
    }

    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }
}
