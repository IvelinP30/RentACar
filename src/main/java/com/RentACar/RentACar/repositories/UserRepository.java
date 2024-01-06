package com.RentACar.RentACar.repositories;

import com.RentACar.RentACar.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    //User findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT u " +
            "FROM User u " +
            "WHERE " +
            "lower(u.firstName) " +
            "LIKE :#{#firstName == null || #firstName.isEmpty()? '%' : #firstName +'%'} " +
            "AND lower(u.lastName) " +
            "LIKE :#{#lastName == null || #lastName.isEmpty()? '%' : #lastName +'%'}")
    Page<User> filterUserPages(Pageable pageable, String firstName, String lastName);

    User findUserByNum(int userNum);
}
