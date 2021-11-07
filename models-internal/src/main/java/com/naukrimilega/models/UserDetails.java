package com.naukrimilega.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobileNo;
    private String experienceYear;
    private String experienceMonth;
    private Date dateOfBirth;
    private EducationDetails education;
    private ScoreCardDetails scoreCardDetails;
}
