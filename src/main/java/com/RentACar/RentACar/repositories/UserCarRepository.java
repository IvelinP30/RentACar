package com.RentACar.RentACar.repositories;

import com.RentACar.RentACar.entities.UserCar;
import com.RentACar.RentACar.entities.keys.UserCarKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCarRepository extends JpaRepository<UserCar, UserCarKey> {

}
