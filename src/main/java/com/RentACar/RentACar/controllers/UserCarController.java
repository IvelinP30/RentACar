package com.RentACar.RentACar.controllers;

import com.RentACar.RentACar.entities.UserCar;
import com.RentACar.RentACar.payload.response.RentResponse;
import com.RentACar.RentACar.repositories.CarRepository;
import com.RentACar.RentACar.repositories.UserCarRepository;
import com.RentACar.RentACar.repositories.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/rent")
public class UserCarController {
    private final UserCarRepository userCarRepo;
    private final UserRepository userRepo;
    private final CarRepository carRepo;

    public UserCarController(UserCarRepository userCarRepo, UserRepository userRepo, CarRepository carRepo) {
        this.userCarRepo = userCarRepo;
        this.userRepo = userRepo;
        this.carRepo = carRepo;
    }

    @GetMapping("/fetch")
    public List<RentResponse> getAllOrders(){
        List<UserCar> rents = userCarRepo.findAll();
        List<RentResponse> rentResponseList = new ArrayList<>();
        for(UserCar rent: rents){
            RentResponse rentResponse = new RentResponse();
            rentResponse.setUserName(rent.getUser().getFullName());
            rentResponse.setCar(rent.getCar().getBrandAndModel());
            rentResponse.setStartDate(rent.getStartDate());
            rentResponse.setFinishDate(rent.getFinishDate());

            rentResponseList.add(rentResponse);
        }
        return rentResponseList;
    }
}