package com.androidhacks7.weatherapp.currentweather.data.network;

import android.arch.lifecycle.LiveData;

import com.androidhacks7.weatherapp.currentweather.data.db.entity.ForecastWeatherResponse;

public interface WeatherNetworkDataSource {
    public void fetchForecastWeather(String city);

    public LiveData<ForecastWeatherResponse> getWeather();
}
