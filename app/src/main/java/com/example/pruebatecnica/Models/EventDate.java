package com.example.pruebatecnica.Models;

import java.util.List;

public class EventDate {
    private Links links;
    private  String id;
    private  String neo_reference_id;
    private String name;
    private  String nasa_jpl_url;
    private  double absolute_magnitude_h;
    private EstimatedDiameter estimated_diameter;
    private boolean is_potentially_hazardous_asteroid;
    private List<CloseApproachData> close_approach_data;
    private  boolean is_sentry_object;

    public EventDate() {
    }

    public EventDate(Links links, String id, String neo_reference_id, String name, String nasa_jpl_url, double absolute_magnitude_h, EstimatedDiameter estimated_diameter, boolean is_potentially_hazardous_asteroid, List<CloseApproachData> close_approach_data, boolean is_sentry_object) {
        this.links = links;
        this.id = id;
        this.neo_reference_id = neo_reference_id;
        this.name = name;
        this.nasa_jpl_url = nasa_jpl_url;
        this.absolute_magnitude_h = absolute_magnitude_h;
        this.estimated_diameter = estimated_diameter;
        this.is_potentially_hazardous_asteroid = is_potentially_hazardous_asteroid;
        this.close_approach_data = close_approach_data;
        this.is_sentry_object = is_sentry_object;
    }

    public EventDate(String name) {
        this.name = name;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNeo_reference_id() {
        return neo_reference_id;
    }

    public void setNeo_reference_id(String neo_reference_id) {
        this.neo_reference_id = neo_reference_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNasa_jpl_url() {
        return nasa_jpl_url;
    }

    public void setNasa_jpl_url(String nasa_jpl_url) {
        this.nasa_jpl_url = nasa_jpl_url;
    }

    public double getAbsolute_magnitude_h() {
        return absolute_magnitude_h;
    }

    public void setAbsolute_magnitude_h(double absolute_magnitude_h) {
        this.absolute_magnitude_h = absolute_magnitude_h;
    }

    public EstimatedDiameter getEstimated_diameter() {
        return estimated_diameter;
    }

    public void setEstimated_diameter(EstimatedDiameter estimated_diameter) {
        this.estimated_diameter = estimated_diameter;
    }

    public boolean isIs_potentially_hazardous_asteroid() {
        return is_potentially_hazardous_asteroid;
    }

    public void setIs_potentially_hazardous_asteroid(boolean is_potentially_hazardous_asteroid) {
        this.is_potentially_hazardous_asteroid = is_potentially_hazardous_asteroid;
    }

    public List<CloseApproachData> getClose_approach_data() {
        return close_approach_data;
    }

    public void setClose_approach_data(List<CloseApproachData> close_approach_data) {
        this.close_approach_data = close_approach_data;
    }

    public boolean isIs_sentry_object() {
        return is_sentry_object;
    }

    public void setIs_sentry_object(boolean is_sentry_object) {
        this.is_sentry_object = is_sentry_object;
    }
}
