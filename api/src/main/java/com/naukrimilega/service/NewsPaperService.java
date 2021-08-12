package com.naukrimilega.service;

import com.google.inject.Inject;
import com.naukrimilega.dao.NewsPaperJobsDAO;
import com.naukrimilega.models.NewspaperJobs;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class NewsPaperService {

  private final NewsPaperJobsDAO newsPaperJobsDAO;
  @Inject
  public NewsPaperService(NewsPaperJobsDAO newsPaperJobsDAO){
    this.newsPaperJobsDAO=newsPaperJobsDAO;
  }

  /**
   * Create newspaper jobs given jobs and the data to be inserted
   * @param newspaperJobs
   * @return Boolean
   */
  public Boolean addNewsPaperJobsDetails(NewspaperJobs newspaperJobs) {
    String nodeName = deriveDailyJobsName();
    newspaperJobs.setId(generateRandomStringForId());
    newspaperJobs.setPublishedOn(LocalDate.now().toString());
    return newsPaperJobsDAO.addNewsPaperJobs(nodeName, newspaperJobs);
  }

  /**
   * Retrieve a job given job type
   * @return List(NewspaperJobs)
   */
  public List<NewspaperJobs> fetchNewsPaperJobsResponse() {
    String nodeName = deriveDailyJobsName();
    return newsPaperJobsDAO.fetchNewsPaperJobsBy(nodeName);
  }

  private String deriveDailyJobsName() {
    return "NewspaperJobs";
  }

  private String generateRandomStringForId() {
    return UUID.randomUUID().toString() + LocalDateTime.now().toString();
  }

  public List<NewspaperJobs> fetchNewsPaperJobsByDateResponse(String date) {
    String nodeName = deriveDailyJobsName();
    return newsPaperJobsDAO.fetchNewsPaperJobsByDate(nodeName, date);
  }
}
