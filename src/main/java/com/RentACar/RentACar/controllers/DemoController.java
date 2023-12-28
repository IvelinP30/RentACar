package com.RentACar.RentACar.controllers;

import com.RentACar.RentACar.entities.Car;
import com.RentACar.RentACar.entities.User;
import com.RentACar.RentACar.repositories.CarRepository;
import com.RentACar.RentACar.repositories.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/client")
public class DemoController {
    private final UserRepository userRepo;
    private final CarRepository carRepo;

    public DemoController(UserRepository userRepo, CarRepository carRepo) {
        this.userRepo = userRepo;
        this.carRepo = carRepo;
    }

    @GetMapping("show/user/cars")
    public List<Car> showUserCar(String firstName, String lastName){
        User selectedUser = userRepo.findByFirstNameAndLastName(firstName,lastName);
        List<Car> result = new ArrayList<>();

        for(Car car:selectedUser.getUserCars()){
            result.add(car);
        }

        return result;
    }

}