package com.androidhacks7.weatherapp.currentweather.di;

import com.androidhacks7.weatherapp.currentweather.data.network.OpenWeatherService;
import com.androidhacks7.weatherapp.currentweather.data.network.WeatherNetworkDataSource;
import com.androidhacks7.weatherapp.currentweather.data.network.WeatherNetworkDataSourceImpl;

import dagger.Module;
import dagger.Provides;

@Module(includes = {OpenWeatherServiceModule.class})
public class WeatherNetworkDataSourceModule {

    @Provides
    public WeatherNetworkDataSource getWeatherNetworkDataSource(OpenWeatherService openWeatherService) {
        return new WeatherNetworkDataSourceImpl(openWeatherService);
    }
}
