package com.RentACar.RentACar.repositories;

import com.RentACar.RentACar.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
