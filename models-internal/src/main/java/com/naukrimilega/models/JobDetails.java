package com.naukrimilega.models;

import lombok.Data;

@Data
public class JobDetails {
    private String jobId;
    private String title;
    private String companyLogoUrl;
    private String description;
    private long publishedOn;
    private long lastDateOn;
    private String category;
    private String firstTag;
    private String secondTag;
    private String location;
    private String state;
    private String destination;
    private String education;
}
