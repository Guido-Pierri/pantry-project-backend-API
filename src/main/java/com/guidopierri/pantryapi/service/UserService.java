package com.guidopierri.pantryapi.service;

import com.guidopierri.pantryapi.model.User;
import com.guidopierri.pantryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Optional<User> getUserByEmailAndPassword(String email, String password){
        return userRepository.getUserByEmailAndPassword(email,password);
    }
    public Optional<User> getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }
    public User createUser(String firstName, String lastName, String email, String password){
        return userRepository.save(new User(firstName,lastName,email,password));

    }
}
