package com.RentACar.RentACar.dataSeed;

import com.RentACar.RentACar.entities.City;
import com.RentACar.RentACar.entities.User;

import java.util.Date;
import java.util.GregorianCalendar;

class UserGenerator {
    static User[] generateUsers(City[] city){
        User[] users = new User[4];
        String[] names = {"Ivan Ivanov", "Pesho Peshev", "Gosho Goshev", "Mitko Mitev"};

        for(int i = 0; i < users.length; i++){
            String[] currentFullName = names[i].split(" ");
            String currentFirstName = currentFullName[0];
            String currentLastName = currentFullName[1];

            users[i] = new User(currentFirstName, currentLastName, city[i], i % 2 == 0, GenerateRandomDate());
        }

        return users;
    }

    private static Date GenerateRandomDate(){ // TODO -> TEST THE RANDOM GERNERATOR
        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1900, 2002);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return gc.getTime();
    }

    private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
