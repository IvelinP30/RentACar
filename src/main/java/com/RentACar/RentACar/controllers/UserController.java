package com.RentACar.RentACar.controllers;

import com.RentACar.RentACar.entities.User;
import com.RentACar.RentACar.payload.request.UserRequest;
import com.RentACar.RentACar.payload.response.UserResponse;
import com.RentACar.RentACar.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<UserResponse> getAllUsers(){
        List<User> users = userRepo.findAll();
        //return result;
        List<UserResponse> result = new ArrayList<>();

        for(User user: users){
            UserResponse userResponse = new UserResponse();
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setNum(user.getNum());
            userResponse.setCity(user.getCity().getName());
            userResponse.setDateBirth(user.getBirthDate());
            userResponse.setManager(user.isManager());

            result.add(userResponse);
        }
        return result;
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

    @DeleteMapping("/delete")
    public String deleteUser(String fName, String lName){
        List<User> result = userRepo.findByFirstNameAndLastName(fName, lName);
        if(result.isEmpty()){
            return "User not found!";
        }
        for(User user: result){
            userRepo.delete(user);
        }
        return fName + lName + "deleted!";
    }

    @PostMapping("/save")
    public ResponseEntity<?> persistUser(@RequestBody UserRequest userRequest){
        List<User> users = userRepo.findByFirstNameAndLastName(userRequest.getFirstName(), userRequest.getLastName());
        if(users.isEmpty()) {
            userRepo.save(new User(
                    userRequest.getFirstName(),
                    userRequest.getLastName(),
                    userRequest.getNum(),
                    userRequest.getCity(),
                    userRequest.isManager(),
                    userRequest.getBirthDate()));
            return ResponseEntity.ok("User is added!");
        }
        for(User user: users){
            user.setNum(userRequest.getNum());
            user.setCity(userRequest.getCity());
            user.setBirthDate(userRequest.getBirthDate());
            user.setManager(userRequest.isManager());
            userRepo.save(user);
        }
        return ResponseEntity.ok("User is saved!");
    }
}