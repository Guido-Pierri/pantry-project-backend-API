package com.guidopierri.pantryapi.controller;

import com.guidopierri.pantryapi.model.Item;
import com.guidopierri.pantryapi.model.User;
import com.guidopierri.pantryapi.model.UserDTO;
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
      public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
          String firstName = user.getFirstName();
          String lastName = user.getLastName();
          String email = user.getEmail();
          Optional<User> retrievedUser = userService.getUserByEmail(email);
          if (retrievedUser.isPresent()){
              return new ResponseEntity<>(HttpStatus.CONFLICT);
          }else{

          // Logic to create a new user using the provided user object

          User createdUser = userService.createUser(firstName, lastName, email);
          UserDTO createdUserDTO = new UserDTO();
            createdUserDTO.firstName = createdUser.getFirstName();
            createdUserDTO.lastName = createdUser.getLastName();
            createdUserDTO.email = createdUser.getEmail();
            createdUserDTO.itemIds = createdUser.getItemIds();
          return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
      }}

        @GetMapping("/user/{email}")
    public ResponseEntity
                <Optional<UserDTO>> getUser(@PathVariable String email) {
        // Retrieve the user's hashed password from the database using the email
       Optional<User> retrievedUser = userService.getUserByEmail(email);
        Optional<UserDTO> retrievedUserDTO = Optional.of(new UserDTO());
        retrievedUserDTO.orElseThrow().firstName = retrievedUser.orElseThrow().getFirstName();
        retrievedUserDTO.orElseThrow().lastName = retrievedUser.orElseThrow().getLastName();
        retrievedUserDTO.orElseThrow().email = retrievedUser.orElseThrow().getEmail();
        retrievedUserDTO.orElseThrow().itemIds = retrievedUser.orElseThrow().getItemIds();
        List<List<Object>>  info = new ArrayList<>();
        //how can I add value pairs to this list?
        List<Object> body = new ArrayList<>();
        body.add(retrievedUser);
        info.add(body);
            return new ResponseEntity<>(retrievedUserDTO, HttpStatus.OK);
        }

    @PostMapping("/login")
    public ResponseEntity<Optional<UserDTO>>loginUser(@RequestBody User user) {
        String email = user.getEmail();
        String enteredPassword = user.getPassword(); // Password entered by the user

        // Retrieve the user's hashed password from the database using the email
        Optional<User> retrievedUser = userService.getUserByEmail(email);
        Optional<UserDTO> retrievedUserDTO = Optional.of(new UserDTO());
        retrievedUserDTO.orElseThrow().firstName = retrievedUser.orElseThrow().getFirstName();
        retrievedUserDTO.orElseThrow().lastName = retrievedUser.orElseThrow().getLastName();
        retrievedUserDTO.orElseThrow().email = retrievedUser.orElseThrow().getEmail();
        retrievedUserDTO.orElseThrow().itemIds = retrievedUser.orElseThrow().getItemIds();

        String storedHashedPassword = retrievedUser.orElseThrow().getPassword();

        // Compare the plain password entered by the user with the stored hashed password
        if (BCrypt.checkpw(enteredPassword, storedHashedPassword)) {
            // Passwords match, user is authenticated
            return new ResponseEntity<>(retrievedUserDTO, HttpStatus.OK);
        } else {
            // Passwords do not match
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}


