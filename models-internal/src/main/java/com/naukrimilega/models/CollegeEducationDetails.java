package com.naukrimilega.models;

import lombok.Data;

@Data
public class CollegeEducationDetails {

  private String bachelor_degree_university;
  private String bachelor_degree_stream;
  private String bachelor_degree_percentage;

  private String master_degree_university;
  private String master_degree_stream;
  private String master_degree_percentage;

  private String phd_degree_university;
  private String phd_degree_stream;
  private String phd_degree_percentage;

}
