package com.androidhacks7.weatherapp.currentweather.di;

import com.androidhacks7.weatherapp.currentweather.data.network.OpenWeatherServiceAPIKeyInterceptor;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class NetworkModule {

    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new OpenWeatherServiceAPIKeyInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build();
        return okHttpClient;
    }
}
