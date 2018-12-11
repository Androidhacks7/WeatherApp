package com.androidhacks7.weatherapp.currentweather.data.network;

import com.androidhacks7.weatherapp.currentweather.data.db.entity.ForecastWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OpenWeatherService {

    @GET("data/2.5/forecast")
    Call<ForecastWeatherResponse> getCurrentWeather(@Query("q") String cityName);
}
