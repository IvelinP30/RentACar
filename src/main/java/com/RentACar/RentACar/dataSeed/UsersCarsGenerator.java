package com.RentACar.RentACar.dataSeed;

import com.RentACar.RentACar.entities.Car;
import com.RentACar.RentACar.entities.User;
import com.RentACar.RentACar.entities.UserCar;

public class UsersCarsGenerator {
    static UserCar[] CreateUsersCars(User[] users, Car[] cars){ // already initialised users and cars
        int recordsCount = Math.min(users.length, cars.length);

        UserCar[] result = new UserCar[recordsCount];

        for(int i = 0; i < recordsCount; i++){
            result[i] = new UserCar(users[i],cars[recordsCount - i - 1]);
        }

        return result;
    }
}