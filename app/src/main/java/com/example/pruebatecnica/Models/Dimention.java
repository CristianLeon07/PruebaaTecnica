package com.example.pruebatecnica.Models;

import java.text.DecimalFormat;

public class Dimention {
    private double estimated_diameter_min;
    private double estimated_diameter_max;

    public Dimention(double estimated_diameter_min, double estimated_diameter_max) {
        this.estimated_diameter_min = estimated_diameter_min;
        this.estimated_diameter_max = estimated_diameter_max;
    }

    public double getEstimated_diameter_min() {
        return estimated_diameter_min;
    }

    public void setEstimated_diameter_min(double estimated_diameter_min) {
        this.estimated_diameter_min = estimated_diameter_min;
    }

    public double getEstimated_diameter_max() {

        return estimated_diameter_max ;

    }

    public void setEstimated_diameter_max(double estimated_diameter_max) {
        this.estimated_diameter_max = estimated_diameter_max;
    }
}
