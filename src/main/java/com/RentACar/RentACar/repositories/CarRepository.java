package com.RentACar.RentACar.repositories;

import com.RentACar.RentACar.entities.Car;
import com.RentACar.RentACar.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT c " +
            "FROM Car c " +
            "WHERE " +
            "c.brand = :brand AND c.model = :model"
    )
    List<Car> findByBrandAndModel(String brand, String model);
    @Query("SELECT c " +
            "FROM Car c " +
            "WHERE " +
            "c.brand " +
            "= :#{#brand == null || #brand.isEmpty()? '%' : #brand +'%'} " +
            "AND c.model" +
            "= :#{#model == null || #model.isEmpty()? '%' : #model +'%'}"
    )
    Page<Car> filterCarPages(Pageable pageable, String brand, String model);
    @Query("SELECT c " +
            "FROM Car c " +
            "WHERE " +
            "c.brand = :brand"
    )
    List<Car> findByBrand(String brand);
}
