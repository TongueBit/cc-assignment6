package com.coderscampus.application;

public enum AbbreviatedMonths {
    JANUARY("Jan"),
    FEBRUARY("Feb"),
    MARCH("Mar"),
    APRIL("Apr"),
    MAY("May"),
    JUNE("Jun"),
    JULY("Jul"),
    AUGUST("Aug"),
    SEPTEMBER("Sep"),
    OCTOBER("Oct"),
    NOVEMBER("Nov"),
    DECEMBER("Dec");

    private final String abbreviation;

    AbbreviatedMonths(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
    
    public static String fromAbbreviation(String abbreviation) {
        for (AbbreviatedMonths month : AbbreviatedMonths.values()) {
            if (month.getAbbreviation().equalsIgnoreCase(abbreviation)) {
                return month.toString();
            }
        }
        return null;
    }
}