package com.example.pruebatecnica.Models;

public class MissDistance {
    private String astronomical;
    private String lunar;
    private String kilometers;
    private String miles;

    public MissDistance(String astronomical, String lunar, String kilometers, String miles) {
        this.astronomical = astronomical;
        this.lunar = lunar;
        this.kilometers = kilometers;
        this.miles = miles;
    }

    public String getAstronomical() {
        return astronomical;
    }

    public void setAstronomical(String astronomical) {
        this.astronomical = astronomical;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }
}
