package com.naukrimilega.service;

import com.google.inject.Inject;
import com.naukrimilega.dao.GeneralKnowledgeDAO;
import com.naukrimilega.models.GkDetails;
import com.naukrimilega.models.query.QueryData;
import java.util.List;

public class GeneralKnowledgeService {

  private final GeneralKnowledgeDAO generalKnowledgeDAO;
  @Inject
  public GeneralKnowledgeService(GeneralKnowledgeDAO generalKnowledgeDAO) {
    this.generalKnowledgeDAO = generalKnowledgeDAO;
  }

  public Boolean addGkDetails(GkDetails gkDetails) {
    String nodeName = deriveGKNodeName();
    return generalKnowledgeDAO.addGK(nodeName, gkDetails);
  }

  public List<GkDetails> fetchGkDetails(String value) {
    List<GkDetails> gkDetails = null;

    if(value!=null) {
      gkDetails = generalKnowledgeDAO.fetchGK(value);
    }
    if(gkDetails==null) {
      gkDetails = generalKnowledgeDAO.fetchGK(value);
    }
    return gkDetails;
  }

  public Boolean deleteGkDetails(QueryData queryData) {
    return null;
  }

  private String deriveGKNodeName() {
    return "GK";
  }
}
