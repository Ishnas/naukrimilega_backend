package com.naukrimilega.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.inject.Inject;
import com.naukrimilega.firebaseutil.service.IDatabaseService;
import com.naukrimilega.models.SubscribedNumbers;
import java.util.HashMap;

public class SubscribeDAO {

  private final IDatabaseService databaseService;
  TypeReference<HashMap<String, Object>> typeRef;
  TypeReference<SubscribedNumbers> subscribedNumbersTypeReference;

  @Inject
  public SubscribeDAO(IDatabaseService databaseService) {
    this.databaseService = databaseService;
    typeRef = new TypeReference<HashMap<String, Object>>() {};
    subscribedNumbersTypeReference = new TypeReference<SubscribedNumbers>() {};
  }

  public Boolean addSubscription(String nodeName, SubscribedNumbers subscribedNumbers) {
    databaseService.saveData(nodeName, subscribedNumbers);
    return true;  }
}
