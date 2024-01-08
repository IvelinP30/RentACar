package com.RentACar.RentACar.controllers;

import com.RentACar.RentACar.entities.City;
import com.RentACar.RentACar.entities.User;
import com.RentACar.RentACar.repositories.CityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/city")
public class CityController {
    private final CityRepository cityRepo;

    public CityController(CityRepository cityRepo){
        this.cityRepo = cityRepo;
    }

    @GetMapping("/fetch")
    public List<City> getAllCities(){
        return cityRepo.findAll();
    }

    @GetMapping("filter")
    public ResponseEntity<?> filterCities(
            @RequestParam(defaultValue = "") String name,
            int currentPage,
            int perPage){
        Pageable pageable = PageRequest.of(currentPage - 1, perPage);
        Page<City> cities = cityRepo.filterCity(pageable, name.toLowerCase());
        Map<String, Object> response = new HashMap<>();
        response.put("totalCities", cities.getTotalElements());
        response.put("totalPages", cities.getTotalPages());
        response.put("cities", cities.getContent());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteCity(String commaSeparatedCities){
        String[] inputCities = commaSeparatedCities.split("(, *)|(,)");
        List<City> citiesToDelete = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < inputCities.length; i++){
            City currentCity = cityRepo.findCityByName(inputCities[i]);

            if(currentCity != null){
                citiesToDelete.add(currentCity);
            }
        }

        if(citiesToDelete.isEmpty()){
            return ResponseEntity.ok("Cities not found");
        }

        for (City city: citiesToDelete){
            if(!city.getUsers().isEmpty()){
                sb.append(city.getName() + " has users and cannot be deleted!");
                continue;
            }

            cityRepo.delete(city);

            sb.append(String.format("%s with id %s was deleted from database",
                    city.getName(),city.getId()) + "\n");
        }

        return ResponseEntity.ok(sb.toString().trim());
    }

    @PostMapping("insert")
    public ResponseEntity<?> insert(String name){
        City selectedCity = cityRepo.findCityByName(name);

        if(cityRepo.findCityByName(name) != null){
            return ResponseEntity.ok(String.format("%s was already added",name));
        }

        cityRepo.save(new City(name));

        return ResponseEntity.ok(String.format("%s added successfully",name));
    }

    @GetMapping("city/users")
    public ResponseEntity<?> cityUsers(
            @RequestParam(defaultValue = "") String cityName,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "4") Integer recordsPerPage){

        Pageable pageable = PageRequest.of(currentPage - 1, recordsPerPage);

        City selectedCity = cityRepo.findCityByName(cityName);

        if(selectedCity == null){
            return ResponseEntity.ok(String.format("%s was not found", cityName));
        }

        Page<User> userPage = cityRepo.findUsersByCityPageable(cityName.toLowerCase(), pageable);
        Map<String, Object> response = new HashMap<>();

        response.put("users", userPage.getContent());
        response.put("totalPages", userPage.getTotalPages());
        response.put("totalElements", userPage.getTotalElements());

        return ResponseEntity.ok(response);
    }
}
