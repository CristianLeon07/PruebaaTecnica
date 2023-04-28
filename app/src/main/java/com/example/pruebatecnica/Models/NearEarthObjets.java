package com.example.pruebatecnica.Models;

import java.util.List;

public class NearEarthObjets {
    private Object dateInit;
    private Object dateFinish;

    public NearEarthObjets(Object dateInit, Object dateFinish) {
        this.dateInit = dateInit;
        this.dateFinish = dateFinish;
    }

    public Object getDateInit() {
        return dateInit;
    }

    public void setDateInit(Object dateInit) {
        this.dateInit = dateInit;
    }

    public Object getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Object dateFinish) {
        this.dateFinish = dateFinish;
    }
}
