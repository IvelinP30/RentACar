package com.RentACar.RentACar.payload.response;

import com.RentACar.RentACar.entities.City;

import java.util.Date;

    public class UserResponse {
        private String firstName;
        private String lastName;
        private Integer num;
        private String city;
        private Date dateBirth;
        private boolean isManager;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Date getDateBirth() {
            return dateBirth;
        }

        public void setDateBirth(Date dateBirth) {
            this.dateBirth = dateBirth;
        }

        public boolean isManager() {
            return isManager;
        }

        public void setManager(boolean manager) {
            isManager = manager;
        }
    }
