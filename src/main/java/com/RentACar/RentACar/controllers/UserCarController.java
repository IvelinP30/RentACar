package com.RentACar.RentACar.controllers;

import com.RentACar.RentACar.entities.User;
import com.RentACar.RentACar.entities.UserCar;
import com.RentACar.RentACar.payload.request.RentRequest;
import com.RentACar.RentACar.payload.response.RentResponse;
import com.RentACar.RentACar.repositories.CarRepository;
import com.RentACar.RentACar.repositories.UserCarRepository;
import com.RentACar.RentACar.repositories.UserRepository;
import com.RentACar.RentACar.service.UserCarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    @PostMapping("/car")
    public ResponseEntity<?> rentCar(@RequestBody RentRequest rentRequest){
        User user = userRepo.findUserByNum(rentRequest.getUserNum());
        userCarRepo.save(new UserCar(
                user,
                rentRequest.getCar(),
                new Timestamp(System.currentTimeMillis()),
                rentRequest.getFinishDate()));
        return ResponseEntity.ok("New rent added!");

    }

    @DeleteMapping("delete/unused")
    public ResponseEntity<?> deleteUnusedCars(){
        List<UserCar> usersCars = userCarRepo.findAll();
        StringBuilder sb = new StringBuilder();

        for(UserCar userCar: usersCars){
            boolean isCarBeingUsed = UserCarService.CheckIfCarIsBeingUsed(userCarRepo,userCar.getCar());

            if(isCarBeingUsed){
                sb.append("Car with registration number " +
                        userCar.getCar().getRegistrationNum() +
                        " is being used by " + userCar.getUser().getFullName());
                continue;
            }

            userCarRepo.delete(userCar);
            sb.append("Deleted record: " + userCar.getUser() + " " + userCar.getCar() + "\n");
        }

        return ResponseEntity.ok(sb);
    }
}