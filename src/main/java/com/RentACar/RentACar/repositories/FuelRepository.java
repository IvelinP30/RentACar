package com.RentACar.RentACar.repositories;

import com.RentACar.RentACar.entities.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelRepository extends JpaRepository<Fuel, Long> {
    Fuel findFuelByName(String name);
}
