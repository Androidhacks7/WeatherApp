package com.androidhacks7.weatherapp.currentweather.ui.forecast;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.androidhacks7.weatherapp.currentweather.data.repository.ForecastWeatherRepository;

import javax.inject.Inject;

public class ForecastWeatherViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private ForecastWeatherRepository forecastWeatherRepository;

    @Inject
    public ForecastWeatherViewModelFactory(ForecastWeatherRepository forecastWeatherRepository) {
        this.forecastWeatherRepository = forecastWeatherRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ForecastWeatherViewModel(forecastWeatherRepository);
    }
}
