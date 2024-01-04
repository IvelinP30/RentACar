package com.RentACar.RentACar.dataSeed;

import com.RentACar.RentACar.entities.City;
import com.RentACar.RentACar.entities.User;

import java.util.Date;
import java.util.GregorianCalendar;

class UserGenerator {
    static User[] generateUsers(City[] cities){
        String[] names = {"Ivan Ivanov", "Pesho Peshev", "Gosho Goshev", "Mitko Mitev"};
        User[] users = new User[names.length];

        for(int i = 0; i < users.length; i++){
            String[] currentFullName = names[i].split(" ");
            String currentFirstName = currentFullName[0];
            String currentLastName = currentFullName[1];

            users[i] = new User(
                    currentFirstName,
                    currentLastName,
                    cities[randomCityIndex(cities.length)],
                    i % 2 == 0, GenerateRandomDate());        }

        return users;
    }

    private static Date GenerateRandomDate(){
        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1965, 2002);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return gc.getTime();
    }

    private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    private static int randomCityIndex(int cityArrayLength){
        return randBetween(0, cityArrayLength - 1);
    }
}
