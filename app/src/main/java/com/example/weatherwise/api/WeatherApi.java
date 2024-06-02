package com.example.weatherwise.api;


import com.example.weatherwise.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {
//    @GET("weathers/{location}")
//    Call<Weather> getWeather(@Path("location") String location);

    @GET("api/v1/weathers")
    Call<List<Weather>> getWeatherList();
}
