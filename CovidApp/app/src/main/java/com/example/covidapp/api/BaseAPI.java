package com.example.covidapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface BaseAPI {
    String baseURL = "http://192.168.1.11:8081/";
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy HH:mm:ss")
            .create();
}
