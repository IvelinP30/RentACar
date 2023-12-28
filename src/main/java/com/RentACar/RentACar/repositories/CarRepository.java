package com.RentACar.RentACar.repositories;

import com.RentACar.RentACar.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
