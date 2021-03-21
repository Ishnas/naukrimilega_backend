package com.naukrimilega.models.query;

public enum Category {
    STATE("states"),
    DATE("date"),
    TAG_FRESHERS("freshers"),
    TAG_GOVT_JOBS("govt");

    private String actualValue;

    Category(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getActualValue() {
        return actualValue;
    }

    public static Category getCategoryEnum(String value) {
        switch(value) {
            case "states": return Category.STATE;
            case "date": return Category.DATE;
            case "freshers": return Category.TAG_FRESHERS;
            default: throw new RuntimeException("Not implemented yet");
        }
    }
}
