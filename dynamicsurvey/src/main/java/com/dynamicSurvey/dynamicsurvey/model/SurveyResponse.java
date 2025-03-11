
package com.dynamicSurvey.dynamicsurvey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "users_id", unique = true)
    @JsonIgnore
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "surveyResponse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyQuestionAnswer> responses = new ArrayList<>();



    public SurveyResponse() {}


    public SurveyResponse(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public void addResponse(SurveyQuestionAnswer response) {
        responses.add(response);
        response.setSurveyResponse(this);
    }
    public List<SurveyQuestionAnswer> getResponses() { return responses; }
}

