package com.RentACar.RentACar.controllers;

import com.RentACar.RentACar.entities.Car;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for(UserCar rent: rents){
            RentResponse rentResponse = new RentResponse();
            rentResponse.setUserName(rent.getUser().getFullName());
            rentResponse.setCar(String.format("%s %s with number: %s", rent.getCar().getBrand().getName(), rent.getCar().getModel(), rent.getCar().getRegistrationNum()));
            rentResponse.setStartDate(dateFormat.format(rent.getStartDate()));
            rentResponse.setFinishDate(dateFormat.format(rent.getFinishDate()));

            rentResponseList.add(rentResponse);
        }
        return rentResponseList;
    }

    @PostMapping("/rent")
    public ResponseEntity<?> rentCar(@RequestBody RentRequest rentRequest){
        User user = userRepo.findUserByNum(rentRequest.getUserNum());
        Car selectedCar = carRepo.findByRegistrationNum(rentRequest.getCarNum());
        Calendar calendar = Calendar.getInstance();

        if(user == null){
            return ResponseEntity.ok(rentRequest.getUserNum() + " is not registered!");
        }

        if(selectedCar == null){
            return ResponseEntity.ok(
                    "Car with registration number " +
                            rentRequest.getCarNum() +
                            " is not registered!");
        }

        if(UserCarService.CheckIfCarIsBeingUsed(userCarRepo, selectedCar)){
            return ResponseEntity.ok("Selected car is being used and cannot be rented!");
        }

        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, rentRequest.getDaysToBeUsed());
        Date finishDate = calendar.getTime();

        UserCar savedUserCar = userCarRepo.save(new UserCar(
                user,
                selectedCar,
                startDate,
                finishDate
        ));

        return ResponseEntity.ok(
                "User " +
                        savedUserCar.getUser().getFullName() +
                        " rented" + savedUserCar.getCar().getBrandAndModel() +
                        " with registration number " + savedUserCar.getCar().getRegistrationNum() +
                        " for" + rentRequest.getDaysToBeUsed() +
                        " days!" +
                        " Expire date: " +
                        savedUserCar.getFinishDate());

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
                        " is being used by " + userCar.getUser().getFullName() + "\n");
                continue;
            }

            userCarRepo.delete(userCar);
            sb.append("Deleted record: " + userCar.getUser().getFullName() + " with car number: " + userCar.getCar().getRegistrationNum() + "\n");
        }

        return ResponseEntity.ok(sb);
    }
}