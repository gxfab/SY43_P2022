package com.example.zeroday.models;

public enum Frequency {
    // "WEEKLY" = 1,
    // "MONTHLY" = 2,
    // "YEARLY" = 3,
    // "TRIMESTERLY" = 4,
    // "QUARTERLY" = 5,
    // "SEMESTERLY" = 6

    WEEKLY(1, "Weekly", 7),
    MONTHLY(2, "Monthly", 30),
    YEARLY(3, "Yearly", 365),
    TRIMESTERLY(4, "Trimesterly", 90),
    QUARTERLY(5, "Quarterly", 120),
    SEMESTERLY(6, "Semesterly", 180);

    private final int id;
    private final int numberOfdays;
    private final String label;

    Frequency(int id, String label, int numberOfdays) {
        this.id = id;
        this.label = label;
        this.numberOfdays = numberOfdays;
    }

    public String getLabel() {
        return label;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfdays() {
        return numberOfdays;
    }
}
