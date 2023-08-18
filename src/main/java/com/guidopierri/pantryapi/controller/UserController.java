package com.guidopierri.pantryapi.controller;

import com.guidopierri.pantryapi.model.User;
import com.guidopierri.pantryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
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
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        String email = user.getEmail();
        String enteredPassword = user.getPassword(); // Password entered by the user

        // Retrieve the user's hashed password from the database using the email
        User retrievedUser = userService.getUserByEmail(email).orElse(null);

        if (retrievedUser != null) {
            String storedHashedPassword = retrievedUser.getPassword();

            // Compare the plain password entered by the user with the stored hashed password
            if (BCrypt.checkpw(enteredPassword, storedHashedPassword)) {
                // Passwords match, user is authenticated
                return new ResponseEntity<>(retrievedUser, HttpStatus.OK);
            } else {
                // Passwords do not match
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


