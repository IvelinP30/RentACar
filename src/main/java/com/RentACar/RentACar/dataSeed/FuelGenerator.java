package com.RentACar.RentACar.dataSeed;

import com.RentACar.RentACar.entities.Fuel;

class FuelGenerator {
    static Fuel[] fuelsGenerator(){
        String[] fuels = {"Petrol", "Diesel", "LPG", "Hybrid"};
        Fuel[] result = new Fuel[fuels.length];

        for(int i = 0; i < fuels.length; i++){
            result[i] = new Fuel(fuels[i]);
        }

        return result;
    }
}
