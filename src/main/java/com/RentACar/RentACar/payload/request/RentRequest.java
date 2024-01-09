package com.RentACar.RentACar.payload.request;

import com.RentACar.RentACar.entities.Car;

import java.util.Date;

public class RentRequest {
    private String userNum;
    private String carNum;
    private int daysToBeUsed;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public int getDaysToBeUsed() {
        return daysToBeUsed;
    }

    public void setDaysToBeUsed(int daysToBeUsed) {
        this.daysToBeUsed = daysToBeUsed;
    }
}
