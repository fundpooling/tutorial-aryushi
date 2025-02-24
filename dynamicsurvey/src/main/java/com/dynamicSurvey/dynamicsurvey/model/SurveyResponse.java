package com.dynamicSurvey.dynamicsurvey.model;

import jakarta.persistence.*;

@Entity
public class SurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;

    // Default constructor (required by JPA)
    public SurveyResponse() {}

    // Parameterized constructor
    public SurveyResponse(Long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "SurveyResponse{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
