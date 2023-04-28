package com.example.pruebatecnica.Models;

public class ModelResponseEndpoint {
    private Links links;
    private  int element_count;
    private Object near_earth_objects;

    public ModelResponseEndpoint(Links links, int element_count, NearEarthObjets near_earth_objects) {
        this.links = links;
        this.element_count = element_count;
        this.near_earth_objects = near_earth_objects;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public int getElement_count() {
        return element_count;
    }

    public void setElement_count(int element_count) {
        this.element_count = element_count;
    }

    public Object getNear_earth_objects() {
        return near_earth_objects;
    }

    public void setNear_earth_objects(NearEarthObjets near_earth_objects) {
        this.near_earth_objects = near_earth_objects;
    }
}
