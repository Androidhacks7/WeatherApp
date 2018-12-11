package com.androidhacks7.weatherapp.currentweather.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.androidhacks7.weatherapp.currentweather.data.db.ForecastWeatherDao;
import com.androidhacks7.weatherapp.currentweather.data.db.entity.ForecastWeatherResponse;
import com.androidhacks7.weatherapp.currentweather.data.network.WeatherNetworkDataSource;

import java.util.List;

import javax.inject.Inject;

public class ForecastWeatherRepositoryImpl implements ForecastWeatherRepository {

    private ForecastWeatherDao forecastWeatherDao;
    private WeatherNetworkDataSource weatherNetworkDataSource;

    @Inject
    public ForecastWeatherRepositoryImpl(ForecastWeatherDao forecastWeatherDao, WeatherNetworkDataSource weatherNetworkDataSource) {
        this.forecastWeatherDao = forecastWeatherDao;
        this.weatherNetworkDataSource = weatherNetworkDataSource;

        weatherNetworkDataSource.getWeather().observeForever(new Observer<ForecastWeatherResponse>() {
            @Override
            public void onChanged(@Nullable ForecastWeatherResponse forecastWeatherResponse) {
                persistFetchedCurrentWeather(forecastWeatherResponse);
            }
        });
    }

    @Override
    public void initWeatherData(String city) {
        if (!isPresentInDatabase(city)) {
            weatherNetworkDataSource.fetchForecastWeather(city);
        }
    }

    @Override
    public LiveData<ForecastWeatherResponse> getForecastWeather(String city) {
        LiveData<ForecastWeatherResponse> weatherInfo = forecastWeatherDao.getWeatherInformation(city);
        return weatherInfo;
    }

    @Override
    public List<String> getRecents() {
        return forecastWeatherDao.getRecents();
    }

    private void persistFetchedCurrentWeather(ForecastWeatherResponse forecastWeatherResponse) {
        if (forecastWeatherResponse == null) {
            return;
        }
        forecastWeatherResponse.setCityName(forecastWeatherResponse.getCity().getName());
        forecastWeatherDao.upsert(forecastWeatherResponse);
    }

    private boolean isPresentInDatabase(String city) {
        return forecastWeatherDao.isCityPresent(city) != 0;
    }
}
