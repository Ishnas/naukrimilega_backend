package com.naukrimilega.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobileNo;
    private int experienceYear;
    private int experienceMonth;
    private Date dateOfBirth;
    private EducationDetails education;
    private ScoreCardDetails scoreCardDetails;
}
