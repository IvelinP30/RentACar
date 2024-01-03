package com.RentACar.RentACar.dataSeed;

import com.RentACar.RentACar.entities.Category;

class CategoryGenerator {
    static Category[] categoriesGenerator(){
        String[] categories = {"Sedan", "Wagon", "Coupe", "Hatchback"};
        Category[] result = new Category[categories.length];

        for(int i = 0; i < categories.length; i++){
            result[i] = new Category(categories[i]);
        }

        return result;
    }
}
