package com.dynamicSurvey.dynamicsurvey.service;

import com.dynamicSurvey.dynamicsurvey.model.User;
import com.dynamicSurvey.dynamicsurvey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save individual response
    public void saveUser(User response) {
        userRepository.save(response);
    }

    // Get all responses
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
