package com.RentACar.RentACar.controllers;

import com.RentACar.RentACar.entities.*;
import com.RentACar.RentACar.payload.request.CarRequest;
import com.RentACar.RentACar.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/car")
public class CarController {
    private final CarRepository carRepo;
    private final FuelRepository fuelRepo;
    private final CategoryRepository categoryRepo;
    private final BrandRepository brandRepo;

    public CarController(
            CarRepository carRepo,
            FuelRepository fuelRepo,
            CategoryRepository categoryRepo,
            BrandRepository brandRepo) {
        this.carRepo = carRepo;
        this.fuelRepo = fuelRepo;
        this.categoryRepo = categoryRepo;
        this.brandRepo = brandRepo;
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
    @PostMapping("/save")
    public ResponseEntity<?> addCar(@RequestBody CarRequest carRequest) throws ParseException {
        if(carRepo.findByRegistrationNum(carRequest.getRegistrationNum()) != null){
            return ResponseEntity.ok("Car already exists!");
        }

        if(brandRepo.findBrandByName(carRequest.getBrand()) == null){
            return ResponseEntity.ok("Our application doesn't with " + carRequest.getBrand() + " cars!");
        }

        if(fuelRepo.findFuelByName(carRequest.getFuel()) == null){
            return ResponseEntity.ok(carRequest.getFuel() + " is unknown fuel!");
        }

        if(categoryRepo.findCategoryByName(carRequest.getCategory()) == null){
            return ResponseEntity.ok(carRequest.getCategory() + " is not supported category!");
        }

        if(brandRepo.findBrandByName(carRequest.getBrand()) == null){
            return ResponseEntity.ok(carRequest.getBrand() + " is not supported brand!");
        }

        Brand selectedBrand = brandRepo.findBrandByName(carRequest.getBrand());
        Fuel selectedFuel = fuelRepo.findFuelByName(carRequest.getFuel());
        Category selectedCategory = categoryRepo.findCategoryByName(carRequest.getCategory());

        Date dateManufactured = new SimpleDateFormat("dd/MM/yyyy").parse(carRequest.getDateManufactured());

        Car car = new Car(
                selectedBrand,
                carRequest.getModel(),
                selectedCategory,
                selectedFuel,
                null,
                carRequest.getRegistrationNum(),
                dateManufactured);

        carRepo.save(car);

        return ResponseEntity.ok(carRequest.getBrand() + " " +
                carRequest.getModel() + " " +
                carRequest.getRegistrationNum() +
                " is added successfully!");
    }
}