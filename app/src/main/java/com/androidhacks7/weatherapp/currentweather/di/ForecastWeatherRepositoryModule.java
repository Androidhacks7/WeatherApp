package com.androidhacks7.weatherapp.currentweather.di;

import com.androidhacks7.weatherapp.currentweather.data.db.ForecastWeatherDao;
import com.androidhacks7.weatherapp.currentweather.data.network.WeatherNetworkDataSource;
import com.androidhacks7.weatherapp.currentweather.data.repository.ForecastWeatherRepository;
import com.androidhacks7.weatherapp.currentweather.data.repository.ForecastWeatherRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module (includes = {ForecastWeatherDatabaseModule.class, WeatherNetworkDataSourceModule.class})
public class ForecastWeatherRepositoryModule {

    @Provides
    public ForecastWeatherRepository forecastWeatherRepository(ForecastWeatherDao forecastWeatherDao, WeatherNetworkDataSource weatherNetworkDataSource) {
        ForecastWeatherRepositoryImpl forecastWeatherRepository = new ForecastWeatherRepositoryImpl(forecastWeatherDao, weatherNetworkDataSource);
        return forecastWeatherRepository;
    }
}
