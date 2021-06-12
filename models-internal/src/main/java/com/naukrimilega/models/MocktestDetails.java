package com.naukrimilega.models;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class MocktestDetails {

  private String id;
  private String subject;
  private String questionPaper;
  private String question;
  private String answer;
  private List<OptionDetails> options;
  private Date publishDate;
}
