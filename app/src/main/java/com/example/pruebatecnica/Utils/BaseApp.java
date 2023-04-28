package com.example.pruebatecnica.Utils;

import android.app.Application;

import com.example.pruebatecnica.BdLocal.AdminSQLite;

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.initializeInstance( new AdminSQLite(this));
    }
}
