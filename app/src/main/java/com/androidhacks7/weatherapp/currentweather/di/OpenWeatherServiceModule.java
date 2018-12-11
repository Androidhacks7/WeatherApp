package com.androidhacks7.weatherapp.currentweather.di;

import com.androidhacks7.weatherapp.currentweather.data.network.OpenWeatherService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module (includes = NetworkModule.class)
public class OpenWeatherServiceModule {

    @Provides
    public OpenWeatherService openWeatherService(OkHttpClient okHttpClient) {
        OpenWeatherService openWeatherService = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherService.class);
        return openWeatherService;
    }


}
