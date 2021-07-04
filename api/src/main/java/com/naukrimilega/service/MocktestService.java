package com.naukrimilega.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.naukrimilega.dao.MocktestDAO;
import com.naukrimilega.models.MocktestDetails;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class MocktestService {

  private final MocktestDAO mocktestDAO;
  @Inject
  public MocktestService(MocktestDAO mocktestDAO){
    this.mocktestDAO=mocktestDAO;
  }

  /**
   * Create a mocktest given mocktest details and the data to be inserted
   * @param mocktestDetails
   * @return Boolean
   */
  public Boolean addMocktestDetails(MocktestDetails mocktestDetails) {
    String nodeName = deriveJobsNodeName();
    mocktestDetails.setId(generateRandomStringForId());
    return mocktestDAO.addMocktest(nodeName, mocktestDetails);
  }

  /**
   * Retrieve a mocktest given
   * @return List(MocktestDetails)
   */
  public List<MocktestDetails> fetchMocktestResponse(String subject, String questionpaper) {
    String nodeName = deriveJobsNodeName();
    return mocktestDAO.fetchMocktestBy(nodeName, subject, questionpaper);
  }


  private String deriveJobsNodeName() {
    return "mocktests";
  }

  private String generateRandomStringForId() {
    return UUID.randomUUID().toString() + LocalDateTime.now().toString();
  }
}
