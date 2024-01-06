package com.RentACar.RentACar.payload.request;

import com.RentACar.RentACar.entities.Car;

import java.util.Date;

public class RentRequest {
    private int userNum;
    private Car car;
    private Date startDate;
    private Date finishDate;

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
