package com.RentACar.RentACar.controllers;

import com.RentACar.RentACar.entities.User;
import com.RentACar.RentACar.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/fetch")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/pages")
    public ResponseEntity<?> getUserPages(@RequestParam(defaultValue = "") String fName,
                                          @RequestParam(defaultValue = "") String lName,
                                          @RequestParam(defaultValue = "1") int currentPage,
                                          @RequestParam(defaultValue = "3") int perPage){
        Pageable pageable = PageRequest.of(currentPage - 1, perPage);
        Page<User> userPage = userRepo.filterUserPages(pageable, fName.toLowerCase(), lName.toLowerCase());
        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("totalPages", userPage.getTotalPages());
        response.put("totalElements", userPage.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/name")
    public ResponseEntity<?> findUserByFullName(String fName, String lName){
        List<User> result = userRepo.findByFirstNameAndLastName(fName, lName);
        return ResponseEntity.ok(result.isEmpty()? "Not Found!" : result);
    }
}