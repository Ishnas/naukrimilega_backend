package com.naukrimilega.service;

import com.google.inject.Inject;
import com.naukrimilega.dao.DailyJobsDAO;
import com.naukrimilega.models.DailyJobs;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class DailyJobsService {

  private final DailyJobsDAO dailyJobsDAO;
  @Inject
  public DailyJobsService(DailyJobsDAO dailyJobsDAO){
    this.dailyJobsDAO=dailyJobsDAO;
  }

  /**
   * Create daily jobs given jobs and the data to be inserted
   * @param dailyJobs
   * @return Boolean
   */
  public Boolean addDailyJobsDetails(DailyJobs dailyJobs) {
    String nodeName = deriveDailyJobsName();
    dailyJobs.setId(generateRandomStringForId());
    return dailyJobsDAO.addDailyJobs(nodeName, dailyJobs);
  }

  /**
   * Retrieve a score given score type
   * @return List(DailyJobs)
   */
  public List<DailyJobs> fetchDailyJobsResponse() {
    String nodeName = deriveDailyJobsName();
    return dailyJobsDAO.fetchDailyJobs(nodeName);
  }

  private String deriveDailyJobsName() {
    return "DailyJobs";
  }

  private String generateRandomStringForId() {
    return UUID.randomUUID().toString() + LocalDateTime.now().toString();
  }
}
