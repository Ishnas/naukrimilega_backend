package com.naukrimilega.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.naukrimilega.firebaseutil.model.FilterByType;
import com.naukrimilega.firebaseutil.model.FilterParam;
import com.naukrimilega.firebaseutil.service.IDatabaseService;
import com.naukrimilega.models.NewspaperJobs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsPaperJobsDAO {

  private final IDatabaseService databaseService;
  TypeReference<HashMap<String, Object>> typeRef;
  TypeReference<NewspaperJobs> newspaperJobsTypeReference;

  @Inject
  public NewsPaperJobsDAO(IDatabaseService databaseService) {
    this.databaseService = databaseService;
    typeRef = new TypeReference<HashMap<String, Object>>() {};
    newspaperJobsTypeReference = new TypeReference<NewspaperJobs>() {};
  }

  public Boolean addNewsPaperJobs(String nodeName, NewspaperJobs newspaperJobs) {
    databaseService.saveData(nodeName, newspaperJobs);
    return true;
  }

  public List<NewspaperJobs> fetchNewsPaperJobsBy(String nodeName) {
    Map<FilterParam, Object> qMap = prepareParamMapFrom();
    return getResultList(nodeName, qMap);
  }

  private List<NewspaperJobs> getResultList(String nodeName, Map<FilterParam, Object> qMap) {
    List<NewspaperJobs> newspaperJobsList = new ArrayList<>();
    Object payload = databaseService.getDataWithFilters(nodeName, FilterByType.CHILD, qMap);
    HashMap<String, Object> newspaperJobsMap = new ObjectMapper().convertValue(payload, typeRef);
    for(String key: newspaperJobsMap.keySet()) {
      NewspaperJobs newspaperJobs = new ObjectMapper().convertValue(newspaperJobsMap.get(key), newspaperJobsTypeReference);
      newspaperJobsList.add(newspaperJobs);
    }
    return newspaperJobsList;
  }

  private Map<FilterParam, Object> prepareParamMapFrom() {
    Map<FilterParam, Object> qMap = new HashMap<>();
    qMap.put(FilterParam.CHILD, "id");

    return qMap;
  }

  public List<NewspaperJobs> fetchNewsPaperJobsByDate(String nodeName, String date) {
    Map<FilterParam, Object> qMap = prepareParamMapFromByDate(date);
    return getResultList(nodeName, qMap);
  }

  private Map<FilterParam, Object> prepareParamMapFromByDate(String date) {
    Map<FilterParam, Object> qMap = new HashMap<>();
    qMap.put(FilterParam.CHILD, "publishedOn");
    qMap.put(FilterParam.EQUAL_TO, date);
    return qMap;
  }
}
