package com.androidhacks7.weatherapp.currentweather.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.androidhacks7.weatherapp.currentweather.data.db.entity.ForecastWeatherResponse;

import javax.inject.Inject;

import retrofit2.Callback;
import retrofit2.Response;

public class WeatherNetworkDataSourceImpl implements WeatherNetworkDataSource {

    private OpenWeatherService openWeatherService;

    private MutableLiveData<ForecastWeatherResponse> forecastWeather = new MutableLiveData<ForecastWeatherResponse>();

    @Inject
    public WeatherNetworkDataSourceImpl(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    @Override
    public void fetchForecastWeather(String city) {

        openWeatherService.getCurrentWeather(city).enqueue(new Callback<ForecastWeatherResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ForecastWeatherResponse> call, Response<ForecastWeatherResponse> response) {
                forecastWeather.postValue(response.body());
            }

            @Override
            public void onFailure(retrofit2.Call<ForecastWeatherResponse> call, Throwable t) {
            }
        });

    }

    @Override
    public LiveData<ForecastWeatherResponse> getWeather() {
        return forecastWeather;
    }
}
