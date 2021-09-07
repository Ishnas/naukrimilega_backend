package com.naukrimilega.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.naukrimilega.firebaseutil.model.FilterByType;
import com.naukrimilega.firebaseutil.model.FilterParam;
import com.naukrimilega.firebaseutil.service.IDatabaseService;
import com.naukrimilega.models.UserDetails;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {

    private final IDatabaseService databaseService;
    TypeReference<UserDetails> userDetailsTypeReference;

    @Inject
    public UserDAO(IDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Boolean addUser(String nodeName, UserDetails userDetails) {
        databaseService.saveData(nodeName, userDetails);
        return true;
    }

    public UserDetails fetchUser(String nodeName, String email) {
        Map<FilterParam, Object> qMap = prepareParamMapFrom(email);
        return getSingleResult(nodeName, qMap);
    }

    private UserDetails getSingleResult(String nodeName, Map<FilterParam, Object> qMap) {
        Object payload = databaseService.getDataWithFilters(nodeName, FilterByType.CHILD, qMap);
        System.out.println(payload);
        UserDetails userDetails = new ObjectMapper().convertValue(payload, userDetailsTypeReference);
        return userDetails;
    }

    private Map<FilterParam, Object> prepareParamMapFrom(String email) {
        Map<FilterParam, Object> qMap = new HashMap<>();
        qMap.put(FilterParam.CHILD, "email");
        qMap.put(FilterParam.EQUAL_TO, email);
        return qMap;
    }
}
