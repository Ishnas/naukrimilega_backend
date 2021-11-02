package com.naukrimilega.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.naukrimilega.firebaseutil.model.FilterByType;
import com.naukrimilega.firebaseutil.model.FilterParam;
import com.naukrimilega.firebaseutil.service.IDatabaseService;
import com.naukrimilega.models.GkDetails;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralKnowledgeDAO {

  private final IDatabaseService databaseService;
  TypeReference<HashMap<String, Object>> typeRef;
  TypeReference<GkDetails> gkDetailsTypeReference;

  @Inject
  public GeneralKnowledgeDAO(IDatabaseService databaseService) {
    this.databaseService = databaseService;
    typeRef = new TypeReference<HashMap<String, Object>>() {};
    gkDetailsTypeReference = new TypeReference<GkDetails>() {};
  }

  public Boolean addGK(String nodeName, GkDetails gkDetails) {
    databaseService.saveData(nodeName, gkDetails);
    return true;
  }

  public List<GkDetails> fetchGK(String nodeName) {
    Map<FilterParam, Object> qMap = prepareParamMapFrom();
    return getResultList(nodeName, qMap);
  }

  private List<GkDetails> getResultList(String nodeName, Map<FilterParam, Object> qMap) {
    List<GkDetails> gkList = new ArrayList<>();
    Object payload = databaseService.getDataWithFilters(nodeName, FilterByType.CHILD, qMap);
    HashMap<String, Object> gkMap = new ObjectMapper().convertValue(payload, typeRef);
    for(String key: gkMap.keySet()) {
      GkDetails gkDetails = new ObjectMapper().convertValue(gkMap.get(key), gkDetailsTypeReference);
      gkList.add(gkDetails);
    }
    return gkList;
  }

  private Map<FilterParam, Object> prepareParamMapFrom() {
    Map<FilterParam, Object> qMap = new HashMap<>();
    qMap.put(FilterParam.CHILD, "gkType");
    return qMap;
  }
}
