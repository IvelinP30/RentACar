package com.RentACar.RentACar.entities.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserCarKey implements Serializable {
    public UserCarKey(Long userId, Long carId) {
        this.userId = userId;
        this.carId = carId;
    }

    public UserCarKey() {
    }

    @Column(name = "user_id")
    Long userId;

    @Column(name = "car_id")
    Long carId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCarKey that = (UserCarKey) o;
        return userId.equals(that.userId) && carId.equals(that.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, carId);
    }
}