package com.guidopierri.pantryapi.controller;

import com.guidopierri.pantryapi.model.User;
import com.guidopierri.pantryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    //@GetMapping("/{email}")
    //public ResponseEntity<Optional<User>> getUser(@PathVariable String email){

      //  return new ResponseEntity<Optional<User>>(userService.getUser(email), HttpStatus.OK);
    //}

    @PostMapping("/retrieve")
    public ResponseEntity<User> retrieveUser(@RequestBody User user) {
        String email = user.getEmail(); // Assuming your User class has a getEmail() method
        String password = user.getPassword();
        User retrievedUser = userService.getUserByEmailAndPassword(email, password).orElse(null);

        if (retrievedUser != null) {
            return new ResponseEntity<>(retrievedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

