package com.naukrimilega.service;

import com.google.inject.Inject;
import com.naukrimilega.dao.BannerJobsDAO;
import com.naukrimilega.models.BannerJobs;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BannerJobsService {

  private final BannerJobsDAO bannerJobsDAO;
  @Inject
  public BannerJobsService(BannerJobsDAO bannerJobsDAO){
    this.bannerJobsDAO=bannerJobsDAO;
  }

  /**
   * Create banner jobs given jobs and the data to be inserted
   * @param bannerJobs
   * @return Boolean
   */
  public Boolean addBannerJobs(BannerJobs bannerJobs) {
    String nodeName = deriveBannerJobsName();
    bannerJobs.setId(generateRandomStringForId());
    return bannerJobsDAO.addBannerJobs(nodeName, bannerJobs);
  }

  /**
   * Retrieve all jobs
   * @return List(BannerJobs)
   */
  public List<BannerJobs> fetchBannerJobsResponse() {
    String nodeName = deriveBannerJobsName();
    return bannerJobsDAO.fetchBannerJobs(nodeName);
  }

  private String deriveBannerJobsName() {
    return "BannerJobs";
  }

  private String generateRandomStringForId() {
    return UUID.randomUUID().toString() + LocalDateTime.now().toString();
  }
}
