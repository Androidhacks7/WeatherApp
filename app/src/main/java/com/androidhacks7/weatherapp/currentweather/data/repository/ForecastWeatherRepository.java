package com.androidhacks7.weatherapp.currentweather.data.repository;

import android.arch.lifecycle.LiveData;

import com.androidhacks7.weatherapp.currentweather.data.db.entity.ForecastWeatherResponse;

import java.util.List;

public interface ForecastWeatherRepository {

    public void initWeatherData(String city);

    public LiveData<ForecastWeatherResponse> getForecastWeather(String city);

    public List<String> getRecents();
}
