package com.naukrimilega.models;

import java.util.Date;
import lombok.Data;

@Data
public class ScoreCardDetails {

  private String id;
  private String userId;
  private String subject;
  private String paperNo;
  private String score;
  private String questionNo;
  private Date dateOfExam;
}
