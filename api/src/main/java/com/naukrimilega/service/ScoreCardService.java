package com.naukrimilega.service;

import com.google.inject.Inject;
import com.naukrimilega.dao.ScoreCardDAO;
import com.naukrimilega.models.ScoreCardDetails;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ScoreCardService {

  private final ScoreCardDAO scoreCardDAO;
  @Inject
  public ScoreCardService(ScoreCardDAO scoreCardDAO){
    this.scoreCardDAO=scoreCardDAO;
  }

  /**
   * Create a score card given score details and the data to be inserted
   * @param scoreCardDetails
   * @return Boolean
   */
  public Boolean addScoreCardDetails(ScoreCardDetails scoreCardDetails) {
    String nodeName = deriveScoreNodeName();
    scoreCardDetails.setId(generateRandomStringForId());
    return scoreCardDAO.addScore(nodeName, scoreCardDetails);
  }

  /**
   * Retrieve a score given score type
   * @return List(ScoreCardDetails)
   */
  public List<ScoreCardDetails> fetchScoreCardResponse(String subject, String paperNo, String userId) {
    String nodeName = deriveScoreNodeName();
    return scoreCardDAO.fetchScoreBy(nodeName, subject, paperNo, userId);
  }


  private String deriveScoreNodeName() {
    return "TestScores";
  }

  private String generateRandomStringForId() {
    return UUID.randomUUID().toString() + LocalDateTime.now().toString();
  }
}
