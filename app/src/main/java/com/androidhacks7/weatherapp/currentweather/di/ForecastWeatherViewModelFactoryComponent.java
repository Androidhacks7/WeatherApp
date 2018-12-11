package com.androidhacks7.weatherapp.currentweather.di;

import com.androidhacks7.weatherapp.currentweather.ui.forecast.ForecastWeatherViewModelFactory;

import dagger.Component;

@Component(modules = ForecastWeatherRepositoryModule.class)
public interface ForecastWeatherViewModelFactoryComponent {

    public ForecastWeatherViewModelFactory getForecastViewModelFactory();
}
