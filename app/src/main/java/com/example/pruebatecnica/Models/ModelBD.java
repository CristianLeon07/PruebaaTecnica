package com.example.pruebatecnica.Models;

import java.lang.ref.PhantomReference;

public class ModelBD {
    private  String id_Asteroid;
    private String name;
    private double diametro_Metros;
    private int peligro;


    public ModelBD( String id_Asteroid,String name, double diametro_Metros, int peligro) {
        this.name = name;
        this.id_Asteroid = id_Asteroid;
        this.diametro_Metros = diametro_Metros;
        this.peligro = peligro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_Asteroid() {
        return id_Asteroid;
    }

    public void setId_Asteroid(String id_Asteroid) {
        this.id_Asteroid = id_Asteroid;
    }

    public double getDiametro_Metros() {
        return diametro_Metros;
    }

    public void setDiametro_Metros(double diametro_Metros) {
        this.diametro_Metros = diametro_Metros;
    }

    public int getPeligro() {
        return peligro;
    }

    public void setPeligro(int peligro) {
        this.peligro = peligro;
    }
}
