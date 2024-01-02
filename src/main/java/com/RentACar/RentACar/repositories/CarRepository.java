package com.RentACar.RentACar.repositories;

import com.RentACar.RentACar.entities.Car;
import com.RentACar.RentACar.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByBrandAndModel(String brand, String model);

    @Query("SELECT c " +
            "FROM Cars c " +
            "WHERE " +
            "u.brand " +
            "= :#{#brand == null || #brand.isEmpty()? '%' : #brand +'%'} " +
            "AND u.model" +
            "= :#{#model == null || #model.isEmpty()? '%' : #model +'%'}" +
            "LIMIT 1"
    )
    Page<Car> filterCarPages(Pageable pageable, String brand, String model);
}
