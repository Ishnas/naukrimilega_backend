package com.naukrimilega.service;

import com.google.inject.Inject;
import com.naukrimilega.dao.UserDAO;
import com.naukrimilega.models.UserDetails;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;
    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Create an user given userDetails
     * @param userDetails
     * @return Boolean
     */
    public Boolean addUser(UserDetails userDetails) {
        String nodeName = deriveNodeNameUsers();
        return userDAO.addUser(nodeName, userDetails);
    }

    private String deriveNodeNameUsers() {
        return "users";
    }

    /**
     * Retrieve a job given job type
     * @return List(JobDetails)
     */

    public UserDetails fetchUser(String email) {
        String nodeName = deriveNodeNameUsers();
        return userDAO.fetchUser(nodeName, email);
    }

    /**
     * Reset password by matching email id
     */

    public String resetPassword(String email, String password){
        String nodeName = deriveNodeNameUsers();
        return userDAO.resetPassword(nodeName, email, password);
    }
}
