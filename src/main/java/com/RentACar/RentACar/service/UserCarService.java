package com.RentACar.RentACar.service;

import com.RentACar.RentACar.entities.Car;
import com.RentACar.RentACar.entities.UserCar;
import com.RentACar.RentACar.repositories.UserCarRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserCarService {

    public static Boolean CheckIfCarIsBeingUsed(UserCarRepository userCarRepo, Car car){
        List<UserCar> usersCars = userCarRepo.findUserCarByCar(car);
        Calendar calendar = Calendar.getInstance();

        for(UserCar userCar: usersCars){
            if (calendar.getTime().before(userCar.getFinishDate())){
                return true;
            }
        }

        return false;
    }
}