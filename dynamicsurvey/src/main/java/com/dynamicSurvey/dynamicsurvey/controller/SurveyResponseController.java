package com.dynamicSurvey.dynamicsurvey.controller;

import com.dynamicSurvey.dynamicsurvey.model.SurveyResponse;
import com.dynamicSurvey.dynamicsurvey.service.SurveyResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/survey")
public class SurveyResponseController {

    @Autowired
    private SurveyResponseService surveyResponseService;

    // POST endpoint to save user response
    @PostMapping("/submit")
    public ResponseEntity<Map<String, String>> submitSurveyResponses(@RequestBody List<SurveyResponse> responses) {
        for (SurveyResponse response : responses) {
            surveyResponseService.saveResponse(response);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Survey responses submitted successfully.");
        return ResponseEntity.ok(response);
    }

    // GET endpoint to retrieve all responses
    @GetMapping("/responses")
    public ResponseEntity<List<SurveyResponse>> getAllResponses() {
        List<SurveyResponse> responses = surveyResponseService.getAllResponses();
        return ResponseEntity.ok(responses);
    }
}
