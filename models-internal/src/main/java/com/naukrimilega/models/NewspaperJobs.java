package com.naukrimilega.models;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class NewspaperJobs {

  private String id;

  private List<String> currentAffairs;

  private List<String> latestGovJobs;

  private List<String> workFromHome;

  private List<String> topCompetitiveExams;

  private List<String> results;

  private Date publishedOn;
}
