package com.naukrimilega.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.naukrimilega.firebaseutil.service.IDatabaseService;
import com.naukrimilega.models.UserDetails;

public class UserDAO {
    private final IDatabaseService databaseService;
    @Inject
    public UserDAO(IDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Boolean addUser(String nodeName, UserDetails userDetails) {
        databaseService.saveData(nodeName, userDetails);
        return true;
    }

    public UserDetails fetchUser(String nodeName, String email) {
        return getSingleResult(nodeName, email);
    }

    private UserDetails getSingleResult(String nodeName, String email) {
        Object payload = databaseService.getData(nodeName);
        UserDetails userDetails = new ObjectMapper().convertValue(payload, new TypeReference<UserDetails>() {});
        return userDetails;
    }
}
