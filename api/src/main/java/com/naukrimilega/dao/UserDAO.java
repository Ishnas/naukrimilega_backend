package com.naukrimilega.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.naukrimilega.firebaseutil.model.FilterByType;
import com.naukrimilega.firebaseutil.model.FilterParam;
import com.naukrimilega.firebaseutil.service.IDatabaseService;
import com.naukrimilega.models.UserDetails;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class UserDAO {

    private final IDatabaseService databaseService;
    TypeReference<HashMap<String, Object>> typeRef;
    TypeReference<UserDetails> userDetailsTypeReference;

    @Inject
    public UserDAO(IDatabaseService databaseService) {
        this.databaseService = databaseService;
        typeRef = new TypeReference<HashMap<String, Object>>() {};
        userDetailsTypeReference = new TypeReference<UserDetails>() {};
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
        UserDetails userDetails = new UserDetails();
        Object payload = databaseService.getDataWithFilters(nodeName, FilterByType.CHILD, qMap);
        System.out.println(payload);
        HashMap<String, Object> userDetailsMap = new ObjectMapper().convertValue(payload, typeRef);
        convertEpochToDate(userDetails);
        for(String key: userDetailsMap.keySet()) {
            userDetails = new ObjectMapper().convertValue(userDetailsMap.get(key), userDetailsTypeReference);
        }
        return userDetails;
    }

    private Map<FilterParam, Object> prepareParamMapFrom(String email) {
        Map<FilterParam, Object> qMap = new HashMap<>();
        qMap.put(FilterParam.CHILD, "email");
        qMap.put(FilterParam.EQUAL_TO, email);
        return qMap;
    }

    private String convertEpochToDate(UserDetails userDetails){
        Date date = new Date(String.valueOf(userDetails.getDateOfBirth()));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        System.out.println(formatted);

        return formatted;
    }
}
