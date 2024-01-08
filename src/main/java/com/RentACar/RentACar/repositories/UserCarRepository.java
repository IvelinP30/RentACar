package com.RentACar.RentACar.repositories;

import com.RentACar.RentACar.entities.Car;
import com.RentACar.RentACar.entities.UserCar;
import com.RentACar.RentACar.entities.keys.UserCarKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCarRepository extends JpaRepository<UserCar, UserCarKey> {
    List<UserCar> findUserCarByCar(Car car);
}
