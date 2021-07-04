package com.naukrimilega.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.naukrimilega.firebaseutil.model.FilterByType;
import com.naukrimilega.firebaseutil.model.FilterParam;
import com.naukrimilega.firebaseutil.service.IDatabaseService;
import com.naukrimilega.models.DailyJobs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyJobsDAO {

  private final IDatabaseService databaseService;
  TypeReference<HashMap<String, Object>> typeRef;
  TypeReference<DailyJobs> dailyJobsTypeReference;

  @Inject
  public DailyJobsDAO(IDatabaseService databaseService) {
    this.databaseService = databaseService;
    typeRef = new TypeReference<HashMap<String, Object>>() {};
    dailyJobsTypeReference = new TypeReference<DailyJobs>() {};
  }

  public Boolean addDailyJobs(String nodeName, DailyJobs dailyJobs) {
    databaseService.saveData(nodeName, dailyJobs);
    return true;
  }

  public List<DailyJobs> fetchDailyJobs(String nodeName) {
    Map<FilterParam, Object> qMap = prepareParamMapFrom();
    return getResultList(nodeName, qMap);
  }

  private List<DailyJobs> getResultList(String nodeName, Map<FilterParam, Object> qMap) {
    List<DailyJobs> dailyjobsList = new ArrayList<>();
    Object payload = databaseService.getDataWithFilters(nodeName, FilterByType.CHILD, qMap);
    HashMap<String, Object> dailyJobsMap = new ObjectMapper().convertValue(payload, typeRef);
    for(String key: dailyJobsMap.keySet()) {
      DailyJobs dailyJobs = new ObjectMapper().convertValue(dailyJobsMap.get(key), dailyJobsTypeReference);
      dailyjobsList.add(dailyJobs);
    }
    return dailyjobsList;
  }

  private Map<FilterParam, Object> prepareParamMapFrom() {
    Map<FilterParam, Object> qMap = new HashMap<>();

    qMap.put(FilterParam.CHILD, "id");

    return qMap;
  }
}
