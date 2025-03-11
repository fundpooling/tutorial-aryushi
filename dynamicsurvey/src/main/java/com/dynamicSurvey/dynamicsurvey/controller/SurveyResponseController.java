
package com.dynamicSurvey.dynamicsurvey.controller;

import com.dynamicSurvey.dynamicsurvey.model.SurveyQuestionAnswer;
import com.dynamicSurvey.dynamicsurvey.model.SurveyResponse;
import com.dynamicSurvey.dynamicsurvey.model.User;
import com.dynamicSurvey.dynamicsurvey.service.SurveyResponseService;
import com.dynamicSurvey.dynamicsurvey.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SurveyResponseController {

    @Autowired
    private SurveyResponseService surveyResponseService;

    @Autowired
    private UserService userService;

    //----------------USER------------------//
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // POST endpoint to save user response
    @PostMapping("/users")
    public ResponseEntity<Map<String, String>> addUser(@RequestBody User user) {
        userService.saveUser(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User created successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }





    //----------------SurveyResponse------------------//
    @PostMapping("/survey/submit/{userId}")
    public ResponseEntity<SurveyResponse> submitSurvey(
            @PathVariable Long userId,
            @RequestBody List<SurveyQuestionAnswer> questions
    ) {
        System.out.println("Received Questions: " + questions);
        return ResponseEntity.ok(surveyResponseService.submitSurvey(userId, questions));
    }

    @GetMapping("/survey/{userId}")
    public ResponseEntity<SurveyResponse> getSurvey(@PathVariable Long userId) {
        return surveyResponseService.getSurveyByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Transactional
    @DeleteMapping("/survey/delete/{userId}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long userId) {
        surveyResponseService.deleteSurvey(userId);
        return ResponseEntity.noContent().build();
    }
}

