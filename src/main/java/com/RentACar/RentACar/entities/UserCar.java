package com.RentACar.RentACar.entities;

import com.RentACar.RentACar.entities.keys.UserCarKey;
import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity()
@Table(name = "users_cars")
public class UserCar {
    @EmbeddedId
    private UserCarKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("carId")
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "start_Date")
    private Date startDate;

    @Column(name = "finish_Date")
    private Date finishDate;

    public UserCar() {
    }

    public UserCar(User user, Car car, Date startDate, Date finishDate) {
        this.id = new UserCarKey(user.getId(), car.getId());
        this.user = user;
        this.car = car;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public UserCarKey getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
