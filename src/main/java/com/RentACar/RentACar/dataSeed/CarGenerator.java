package com.RentACar.RentACar.dataSeed;

import com.RentACar.RentACar.entities.*;

public class CarGenerator {
    static Car[] carsGenerator(Brand[] brand, Fuel[] fuel, Category[] category){ // recieve already instanced objects -> with valid ids
        Car[] cars = new Car[brand.length];

        for(int i = 0; i < brand.length; i++){
            if(brand[i].getName() == "BMW"){
                cars[i] = new Car(brand[i], "540I", category[i],fuel[i], null);
            }
            else if(brand[i].getName() == "VW"){
                cars[i] = new Car(brand[i], "GOLF", category[i],fuel[i], null);
            }
            else if(brand[i].getName() == "Audi"){
                cars[i] = new Car(brand[i], "A6", category[i],fuel[i], null);
            }
            else if(brand[i].getName() == "Mercedes"){
                cars[i] = new Car(brand[i], "E320", category[i],fuel[i], null);
            }
        }

        return cars;
    }
}
