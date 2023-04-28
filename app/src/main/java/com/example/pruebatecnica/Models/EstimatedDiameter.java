package com.example.pruebatecnica.Models;

public class EstimatedDiameter {
    private Dimention kilometers;
    private Dimention meters;
    private Dimention miles;
    private  Dimention feet;

    public EstimatedDiameter(Dimention kilometers, Dimention meters, Dimention miles, Dimention feet) {
        this.kilometers = kilometers;
        this.meters = meters;
        this.miles = miles;
        this.feet = feet;
    }

    public Dimention getKilometers() {
        return kilometers;
    }

    public void setKilometers(Dimention kilometers) {
        this.kilometers = kilometers;
    }

    public Dimention getMeters() {
        return meters;
    }

    public void setMeters(Dimention meters) {
        this.meters = meters;
    }

    public Dimention getMiles() {
        return miles;
    }

    public void setMiles(Dimention miles) {
        this.miles = miles;
    }

    public Dimention getFeet() {
        return feet;
    }

    public void setFeet(Dimention feet) {
        this.feet = feet;
    }
}
