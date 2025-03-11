
package com.dynamicSurvey.dynamicsurvey.service;

import com.dynamicSurvey.dynamicsurvey.model.SurveyQuestionAnswer;
import com.dynamicSurvey.dynamicsurvey.model.SurveyResponse;
import com.dynamicSurvey.dynamicsurvey.model.User;
import com.dynamicSurvey.dynamicsurvey.repository.SurveyResponseRepository;
import com.dynamicSurvey.dynamicsurvey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyResponseService {

    @Autowired
    private SurveyResponseRepository surveyResponseRepository;

    @Autowired
    private UserRepository userRepository;

    public SurveyResponse submitSurvey(Long userId, List<SurveyQuestionAnswer> questions) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        SurveyResponse survey = new SurveyResponse();
        survey.setUser(user);
        for (SurveyQuestionAnswer qa : questions) {
            SurveyQuestionAnswer questionAnswer = new SurveyQuestionAnswer(qa.getQuestion(), qa.getAnswer());
            survey.addResponse(questionAnswer);
        }

        return surveyResponseRepository.save(survey);
    }

    public Optional<SurveyResponse> getSurveyByUserId(Long userId) {
        return surveyResponseRepository.findByUserId(userId);
    }

    public void deleteSurvey(Long userId) {
        surveyResponseRepository.deleteByUserId(userId);
    }


}

