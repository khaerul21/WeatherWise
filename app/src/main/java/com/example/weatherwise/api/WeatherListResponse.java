package com.example.weatherwise.api;

import com.example.weatherwise.model.Weather;

import java.util.List;

public class WeatherListResponse {

    private List<Weather> weatherList;

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }
}
