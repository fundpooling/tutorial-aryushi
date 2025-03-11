package com.dynamicSurvey.dynamicsurvey.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String address;
    private String mobileNumber;


//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private SurveyResponse surveyResponse;

    public User() {}

    public User(String name, String email, String address, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getMobileNumber() { return mobileNumber; }
//    public SurveyResponse getSurveyResponse() { return surveyResponse; }
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
//    public void setSurveyResponse(SurveyResponse surveyResponse) { this.surveyResponse = surveyResponse; }

}
