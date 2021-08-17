package com.naukrimilega.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.naukrimilega.dao.SubscribeDAO;
import com.naukrimilega.models.SubscribedNumbers;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;


@Singleton
@Slf4j
public class SubscribeService {

  private final SubscribeDAO subscribeDAO;
  @Inject
  public SubscribeService(SubscribeDAO subscribeDAO) {
    this.subscribeDAO = subscribeDAO;
  }

  /**
   * Create a subscription given subscribed number and the data to be inserted
   * @param mobileno
   * @return Boolean
   */

  public Boolean addSubscriptionDetails(String mobileno) {
    SubscribedNumbers subscribedNumbers = new SubscribedNumbers();
    String nodeName = deriveSubscriptionNodeName();
    subscribedNumbers.setId(generateRandomStringForId());
    subscribedNumbers.setMobileNo(mobileno);
    return subscribeDAO.addSubscription(nodeName, subscribedNumbers);
  }

  private String deriveSubscriptionNodeName() {
    return "Subscribed numbers";
  }

  private String generateRandomStringForId() {
    return UUID.randomUUID().toString() + LocalDateTime.now().toString();
  }
}
