package com.RentACar.RentACar.dataSeed;

import com.RentACar.RentACar.entities.*;
import com.RentACar.RentACar.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private BrandRepository brandRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private FuelRepository fuelRepo;

    @Autowired
    private UserRepository userRepo;


    @Override
    public void run(String... args) throws Exception {
        loadBrands();
        loadCategories();
        loadCities();
        loadFuel();
    }

    private void loadBrands(){
        List<Brand> brands = Arrays.asList(BrandGenerator.brandsGenerator());
        List<Brand> brandsToSave = new ArrayList<>();

        for(Brand brand: brands){
            if(brandRepo.findBrandByName(brand.getName()) == null){
                brandsToSave.add(brand);
            }
        }

        brandRepo.saveAll(brandsToSave);
    }

        private void loadCategories(){
            List<Category> categories = Arrays.asList(CategoryGenerator.categoriesGenerator());
            List<Category> categoriesToSave = new ArrayList<>();

            for(Category category: categories){
                if(categoryRepo.findCategoryByName(category.getName()) == null){
                    categoriesToSave.add(category);
                }
            }

            categoryRepo.saveAll(categoriesToSave);
        }

        private void loadCities(){
            List<City> cities = Arrays.asList(CityGenerator.citiesGenerator());
            List<City> citiesToSave = new ArrayList<>();

            for(City city: cities){
                if(cityRepo.findCityByName(city.getName()) == null){
                    citiesToSave.add(city);
                }
            }

            cityRepo.saveAll(citiesToSave);
        }

        private void loadFuel(){
            List<Fuel> fuels = Arrays.asList(FuelGenerator.fuelsGenerator());
            List<Fuel> fuelsToSave = new ArrayList<>();

            for(Fuel fuel: fuels){
                if(fuelRepo.findFuelByName(fuel.getName()) == null){
                    fuelsToSave.add(fuel);
                }
            }

            fuelRepo.saveAll(fuelsToSave);
        }

    /*private void loadUsers(){
        List<User> users = Arrays.asList(UserGenerator.generateUsers(cityRepo.findAll().toArray(new City[0])));
        List<User> usersToSave = new ArrayList<>();
        for(User user: users){
            if(){
                usersToSave.add(user);
            }
        }
    }*/
    }
