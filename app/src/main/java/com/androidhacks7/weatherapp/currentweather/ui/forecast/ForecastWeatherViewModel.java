package com.androidhacks7.weatherapp.currentweather.ui.forecast;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.androidhacks7.weatherapp.currentweather.data.db.entity.ForecastWeatherResponse;
import com.androidhacks7.weatherapp.currentweather.data.repository.ForecastWeatherRepository;

import java.util.List;

import javax.inject.Inject;

public class ForecastWeatherViewModel extends ViewModel {

    public ForecastWeatherRepository forecastWeatherRepository;

    @Inject
    public ForecastWeatherViewModel(ForecastWeatherRepository forecastWeatherRepository) {
        this.forecastWeatherRepository = forecastWeatherRepository;
    }

    public void initWeatherData(String city) {
        forecastWeatherRepository.initWeatherData(city);
    }

    public LiveData<ForecastWeatherResponse> getForecastWeather(String cityName) {
        LiveData<ForecastWeatherResponse> weather = forecastWeatherRepository.getForecastWeather(cityName);
        return weather;
    }

    public List<String> getRecents() {
        return forecastWeatherRepository.getRecents();
    }

}
