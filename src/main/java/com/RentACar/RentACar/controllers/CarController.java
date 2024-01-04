package com.RentACar.RentACar.controllers;

import com.RentACar.RentACar.entities.Car;
import com.RentACar.RentACar.entities.User;
import com.RentACar.RentACar.repositories.CarRepository;
import com.RentACar.RentACar.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/car")
public class CarController {
    private final CarRepository carRepo;

    public CarController(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    @GetMapping("/fetch")
    public List<Car> getAllCars(){
        return carRepo.findAll();
    }

    @GetMapping("/find/model")
    public ResponseEntity<?> findCarByBrandAndModel(String Brand, String Model){
        List<Car> result = carRepo.findByBrandAndModel(Brand, Model);
        return ResponseEntity.ok(result != null ?
                result :
                "Not Found!");
    }

    @GetMapping("/find/brand")
    public ResponseEntity<?> findCarByBrand(String Brand){
        List<Car> result = carRepo.findByBrand(Brand);
        return ResponseEntity.ok(result != null ?
                result :
                "Not Found!");
    }
}