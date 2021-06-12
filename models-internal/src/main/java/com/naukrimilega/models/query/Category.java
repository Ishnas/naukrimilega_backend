package com.naukrimilega.models.query;

public enum Category {
    CATEGORY("category"),
    CITY("city"),
    DESIGNATION("designation"),
    EDUCATION("education"),
    ENGINEERINGSTREAMS("engineeringstreams"),
    TOPCOMPANIES("company"),
    STATE("state"),
    DATE("date"),
    TAG_FRESHERS("freshers"),
    TAG_GOVT_JOBS("govt"),
    PUBLISHED_ON("publishedon");

    private String actualValue;

    Category(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getActualValue() {
        return actualValue;
    }

    public static Category getCategoryEnum(String value) {
        switch(value) {
            case "category": return Category.CATEGORY;
            case "city": return Category.CITY;
            case "designation": return Category.DESIGNATION;
            case "education": return Category.EDUCATION;
            case "engineeringstreams": return Category.ENGINEERINGSTREAMS;
            case "company": return Category.TOPCOMPANIES;
            case "state": return Category.STATE;
            case "date": return Category.DATE;
            case "freshers": return Category.TAG_FRESHERS;
            case "publishedon": return Category.PUBLISHED_ON;
            default: throw new RuntimeException("Not implemented yet");
        }
    }
}
