package com.loganalyzer.model.datasource;

public class Requirements {
    private String minimum;
    private String recommended;

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    @Override
    public String toString() {
        return "Requirements{" +
                "minimum='" + minimum + '\'' +
                ", recommended='" + recommended + '\'' +
                '}';
    }
}
