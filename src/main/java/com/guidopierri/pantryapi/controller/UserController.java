package com.guidopierri.pantryapi.controller;

import com.guidopierri.pantryapi.model.Item;
import com.guidopierri.pantryapi.model.User;
import com.guidopierri.pantryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.*;

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
      @PostMapping("/create") // Use POST method to create a new user
      public ResponseEntity<User> createUser(@RequestBody User user) {
          // Logic to create a new user using the provided user object
          String firstName = user.getFirstName();
          String lastName = user.getLastName();
          String email = user.getEmail();
          String password = user.getPassword();
          User createdUser = userService.createUser(firstName, lastName, email, password);
          return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
      }
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
    @PostMapping("/getUser")
    public ResponseEntity<List<Map<String, Object>>>getUser(@RequestBody User user) {
        String email = user.getEmail();
        String enteredPassword = user.getPassword(); // Password entered by the user

        // Retrieve the user's hashed password from the database using the email
        User retrievedUser = userService.getUserByEmail(email).orElse(null);

        List<Map<String, Object>>  info = new ArrayList<>();
        //how can I add value pairs to this list?
        Map<String, Object> body = new HashMap<>();
        body.put("email", retrievedUser.getEmail());
        body.put("firstName",retrievedUser.getFirstName());
        body.put("lastName", retrievedUser.getLastName());
        body.put("userId", retrievedUser.getId());
        body.put("items", retrievedUser.getItemIds());
        info.add(body);
        if (retrievedUser != null) {
            return new ResponseEntity<>(info, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<List<Map<String, Object>>>loginUser(@RequestBody User user) {
        String email = user.getEmail();
        String enteredPassword = user.getPassword(); // Password entered by the user

        // Retrieve the user's hashed password from the database using the email
        User retrievedUser = userService.getUserByEmail(email).orElse(null);

        List<Map<String, Object>>  info = new ArrayList<>();
        //how can I add value pairs to this list?
        Map<String, Object> body = new HashMap<>();
        body.put("email", retrievedUser.getEmail());
        body.put("firstName",retrievedUser.getFirstName());
        body.put("lastName", retrievedUser.getLastName());
        body.put("userId", retrievedUser.getId());
        body.put("items", retrievedUser.getItemIds());
        info.add(body);

        if (retrievedUser != null) {
            String storedHashedPassword = retrievedUser.getPassword();

            // Compare the plain password entered by the user with the stored hashed password
            if (BCrypt.checkpw(enteredPassword, storedHashedPassword)) {
                // Passwords match, user is authenticated
                return new ResponseEntity<>(info, HttpStatus.OK);
            } else {
                // Passwords do not match
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


