package com.naukrimilega.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.naukrimilega.firebaseutil.model.FilterByType;
import com.naukrimilega.firebaseutil.model.FilterParam;
import com.naukrimilega.firebaseutil.service.IDatabaseService;
import com.naukrimilega.models.ScoreCardDetails;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreCardDAO {

  private final IDatabaseService databaseService;
  TypeReference<HashMap<String, Object>> typeRef;
  TypeReference<ScoreCardDetails> typeRefScoreDetails;

  @Inject
  public ScoreCardDAO(IDatabaseService databaseService) {
    this.databaseService = databaseService;
    typeRef = new TypeReference<HashMap<String, Object>>() {};
    typeRefScoreDetails = new TypeReference<ScoreCardDetails>() {};
  }

  public Boolean addScore(String nodeName, ScoreCardDetails scoreCardDetails) {
    databaseService.saveData(nodeName, scoreCardDetails);
    return true;
  }

  public Boolean updateScore(String nodeName, ScoreCardDetails scoreCardDetails) {
    databaseService.updateData(nodeName, scoreCardDetails);
    return true;
  }

  public List<ScoreCardDetails> fetchScoreBy(String nodeName, String subject, String paperNo, String userId) {
    Map<FilterParam, Object> qMap = prepareParamMapFrom(subject, paperNo, userId);
    return getResultList(nodeName, qMap);
  }

  private List<ScoreCardDetails> getResultList(String nodeName, Map<FilterParam, Object> qMap) {
    List<ScoreCardDetails> scoreCardDetailsList = new ArrayList<>();
    Object payload = databaseService.getDataWithFilters(nodeName, FilterByType.CHILD, qMap);
    HashMap<String, Object> scoreDetailsMap = new ObjectMapper().convertValue(payload, typeRef);
    for(String key: scoreDetailsMap.keySet()) {
      ScoreCardDetails scoreCardDetails = new ObjectMapper().convertValue(scoreDetailsMap.get(key), typeRefScoreDetails);
      scoreCardDetailsList.add(scoreCardDetails);
    }
    return scoreCardDetailsList;
  }

  private Map<FilterParam, Object> prepareParamMapFrom(String subject, String paperNo, String userId) {
    Map<FilterParam, Object> qMap = new HashMap<>();

    qMap.put(FilterParam.CHILD, "paperNo");
    qMap.put(FilterParam.START_AT, paperNo);
    qMap.put(FilterParam.CHILD, "subject");
    qMap.put(FilterParam.START_AT, subject);

//    qMap.put(FilterParam.CHILD, "userId");
//    qMap.put(FilterParam.START_AT, userId);

    return qMap;
  }
}
