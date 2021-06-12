package com.naukrimilega.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.naukrimilega.firebaseutil.model.FilterByType;
import com.naukrimilega.firebaseutil.model.FilterParam;
import com.naukrimilega.firebaseutil.service.IDatabaseService;
import com.naukrimilega.models.MocktestDetails;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MocktestDAO {
  private final IDatabaseService databaseService;
  TypeReference<HashMap<String, Object>> typeRef;
  TypeReference<MocktestDetails> typeRefMocktestDetails;

  @Inject
  public MocktestDAO(IDatabaseService databaseService) {
    this.databaseService = databaseService;
    typeRef = new TypeReference<HashMap<String, Object>>() {};
    typeRefMocktestDetails = new TypeReference<MocktestDetails>() {};
  }


  public Boolean addMocktest(String nodeName, MocktestDetails mocktestDetails) {
    databaseService.saveData(nodeName, mocktestDetails);
    return true;
  }



  public List<MocktestDetails> fetchMocktestBy(String nodeName, String subject, String questionpaper) {
    Map<FilterParam, Object> qMap = prepareParamMapFrom(subject, questionpaper);
    return getResultList(nodeName, qMap);
  }


  private List<MocktestDetails> getResultList(String nodeName, Map<FilterParam, Object> qMap) {
    List<MocktestDetails> mocktestDetailsList = new ArrayList<>();
    Object payload = databaseService.getDataWithFilters(nodeName, FilterByType.CHILD, qMap);
    HashMap<String, Object> mocktestDetailsMap = new ObjectMapper().convertValue(payload, typeRef);
    for(String key: mocktestDetailsMap.keySet()) {
      MocktestDetails mocktestDetails = new ObjectMapper().convertValue(mocktestDetailsMap.get(key), typeRefMocktestDetails);
      mocktestDetailsList.add(mocktestDetails);
    }
    return mocktestDetailsList;
  }


  private Map<FilterParam, Object> prepareParamMapFrom(String subject, String questionpaper) {
    Map<FilterParam, Object> qMap = new HashMap<>();

      qMap.put(FilterParam.CHILD, "subject");
      qMap.put(FilterParam.EQUAL_TO, subject);
      qMap.put(FilterParam.CHILD, "questionPaper");
      qMap.put(FilterParam.EQUAL_TO, questionpaper);

//        switch (category) {
//            case DATE: qMap.put(FilterParam.CHILD, DBConstants.PUBLISHED_ON); break;
//            case STATE: qMap.put(FilterParam.CHILD, DBConstants.STATE);
//                        qMap.put(FilterParam.EQUAL_TO, categoryValue);
//                        break;
//            case CATEGORY: qMap.put(FilterParam.CHILD, DBConstants.CATEGORY);
//                           qMap.put(FilterParam.EQUAL_TO, categoryValue);
//                           break;
//            case CITY: qMap.put(FilterParam.CHILD, DBConstants.CITY); break;
//            case DESIGNATION : qMap.put(FilterParam.CHILD, DBConstants.DESIGNATION); break;
//            case EDUCATION: qMap.put(FilterParam.CHILD, DBConstants.EDUCATION); break;
//            case TOPCOMPANIES: qMap.put(FilterParam.CHILD, DBConstants.TOPCOMPANIES); break;
//            case ENGINEERINGSTREAMS: qMap.put(FilterParam.CHILD, DBConstants.ENGINEERINGSTREAMS); break;
//            case TAG_FRESHERS: qMap.put(FilterParam.CHILD, DBConstants.TAG_FRESHERS); break;
//            case TAG_GOVT_JOBS: qMap.put(FilterParam.CHILD, DBConstants.TAG_GOVT_JOBS); break;
//            default: throw new Exception("Not supported yet");
//        }
    return qMap;
  }
}
