package com.naukrimilega.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.naukrimilega.firebaseutil.model.FilterByType;
import com.naukrimilega.firebaseutil.model.FilterParam;
import com.naukrimilega.firebaseutil.service.IDatabaseService;
import com.naukrimilega.models.BannerJobs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannerJobsDAO {

  private final IDatabaseService databaseService;
  TypeReference<HashMap<String, Object>> typeRef;
  TypeReference<BannerJobs> bannerJobsTypeReference;

  @Inject
  public BannerJobsDAO(IDatabaseService databaseService) {
    this.databaseService = databaseService;
    typeRef = new TypeReference<HashMap<String, Object>>() {};
    bannerJobsTypeReference = new TypeReference<BannerJobs>() {};
  }

  public Boolean addBannerJobs(String nodeName, BannerJobs bannerJobs) {
    databaseService.saveData(nodeName, bannerJobs);
    return true;
  }

  public List<BannerJobs> fetchBannerJobs(String nodeName) {
    Map<FilterParam, Object> qMap = prepareParamMapFrom();
    return getResultList(nodeName, qMap);
  }

  private List<BannerJobs> getResultList(String nodeName, Map<FilterParam, Object> qMap) {
    List<BannerJobs> bannerjobsList = new ArrayList<>();
    Object payload = databaseService.getDataWithFilters(nodeName, FilterByType.CHILD, qMap);
    HashMap<String, Object> bannerJobsMap = new ObjectMapper().convertValue(payload, typeRef);
    for(String key: bannerJobsMap.keySet()) {
      BannerJobs bannerJobs = new ObjectMapper().convertValue(bannerJobsMap.get(key), bannerJobsTypeReference);
      bannerjobsList.add(bannerJobs);
    }
    return bannerjobsList;
  }

  private Map<FilterParam, Object> prepareParamMapFrom() {
    Map<FilterParam, Object> qMap = new HashMap<>();
    qMap.put(FilterParam.CHILD, "id");
    return qMap;
  }
}
