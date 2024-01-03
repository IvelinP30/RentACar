package com.RentACar.RentACar.repositories;

import com.RentACar.RentACar.entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c " +
            "FROM City c " +
            "WHERE " +
            "lower(c.name) " +
            "LIKE :#{#name == null || #name.isEmpty()? '%' : '%'+ #name +'%'} ")
    Page<City> filterCity(Pageable pageable, String name);

    City findCityByName(String name);
}