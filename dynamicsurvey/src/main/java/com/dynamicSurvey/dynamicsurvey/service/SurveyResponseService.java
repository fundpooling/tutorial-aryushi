package com.dynamicSurvey.dynamicsurvey.service;

import com.dynamicSurvey.dynamicsurvey.model.SurveyResponse;
import com.dynamicSurvey.dynamicsurvey.repository.SurveyResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyResponseService {

    @Autowired
    private SurveyResponseRepository surveyResponseRepository;

    // Save individual response
    public void saveResponse(SurveyResponse response) {
        surveyResponseRepository.save(response);
    }

    // Get all responses
    public List<SurveyResponse> getAllResponses() {
        return surveyResponseRepository.findAll();
    }
}
