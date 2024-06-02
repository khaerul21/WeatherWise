package com.example.weatherwise.model;

import java.util.ArrayList;

public class Weather {
    private int id;
    private String city;
    private String country;
    private double latitude;
    private double longitude;
    private double temperature;
    private String weather_description;
    private int humidity;
    private double wind_speed;
    private ArrayList<Forecast> forecast;

    public Weather(int id, String city, String country, double latitude, double longitude, double temperature, String weather_description, int humidity, double wind_speed, ArrayList<Forecast> forecast) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.weather_description = weather_description;
        this.humidity = humidity;
        this.wind_speed = wind_speed;
        this.forecast = forecast;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getWeather_description() {
        return weather_description;
    }

    public void setWeather_description(String weather_description) {
        this.weather_description = weather_description;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public ArrayList<Forecast> getForecasts() {
        return forecast;
    }

    public void setForecasts(ArrayList<Forecast> forecasts) {
        this.forecast = forecast;
    }

}