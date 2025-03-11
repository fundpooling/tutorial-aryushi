
package com.dynamicSurvey.dynamicsurvey.model;
import jakarta.persistence.*;

@Entity
@Table(name = "survey_questions_answers")
public class SurveyQuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "survey_response_id")
    private SurveyResponse surveyResponse;

    public SurveyQuestionAnswer() {}

    public SurveyQuestionAnswer(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void setSurveyResponse(SurveyResponse surveyResponse) {
        this.surveyResponse = surveyResponse;
    }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}


