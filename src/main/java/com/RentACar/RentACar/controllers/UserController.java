package com.RentACar.RentACar.controllers;

import com.RentACar.RentACar.entities.User;
import com.RentACar.RentACar.payload.request.UserRequest;
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
        List<User> result = userRepo.findAll();
        return result;

        /*List<UserResponse> result = new ArrayList<>();
        for(User user: userRepo.findAll()){
            result.add(new UserResponse(){
            });
        }
        return result;*/
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
            for (User user : users) {
                user.setCity(userRequest.getCity());
                user.setBirthDate(userRequest.getBirthDate());
                user.setManager(userRequest.isManager());
                return ResponseEntity.ok("User" + user + "is added!");
            }
        }
        return ResponseEntity.ok("User" + userRepo.save(new User(userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getCity(),
                userRequest.isManager(),
                userRequest.getBirthDate())) + "is saved!");
    }
}