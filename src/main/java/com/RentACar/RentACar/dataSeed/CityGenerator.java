package com.RentACar.RentACar.dataSeed;

import com.RentACar.RentACar.entities.City;

class CityGenerator {
    static City[] citiesGenerator(){
        String[] cities = {"Stara Zagora", "Varna", "Burgas", "Plovdiv"};
        City[] result = new City[cities.length];

        for(int i = 0; i < cities.length; i++){
            result[i] = new City(cities[i]);
        }

        return result;
    }
}
