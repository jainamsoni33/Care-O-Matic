package com.example.caro_matic.caro_matic;

public class Disease {
    private String name;
    private String percent;

    public Disease(String name, String percent) {
        this.name = name;
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public String getPercent() {
        return percent;
    }
}
