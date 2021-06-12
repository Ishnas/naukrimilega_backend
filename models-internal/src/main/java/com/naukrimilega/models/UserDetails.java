package com.naukrimilega.models;

import java.util.Date;
import lombok.Data;

@Data
public class UserDetails {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private int mobile_no;

    private int experience_year;

    private int experience_month;

    private Date date_of_birth;

    private EducationDetails education;
}
