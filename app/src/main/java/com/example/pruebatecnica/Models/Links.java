package com.example.pruebatecnica.Models;

public class Links {
    private  String next;
    private String previous;
    private String self;

    public Links(String next, String previous, String self) {
        this.next = next;
        this.previous = previous;
        this.self = self;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}
