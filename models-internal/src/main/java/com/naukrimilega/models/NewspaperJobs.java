package com.naukrimilega.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewspaperJobs {

  private String id;

  private List<String> currentAffairs;

  private List<String> latestGovJobs;

  private List<String> workFromHome;

  private List<String> topCompetitiveExams;

  private List<String> results;

  private String publishedOn;
}
