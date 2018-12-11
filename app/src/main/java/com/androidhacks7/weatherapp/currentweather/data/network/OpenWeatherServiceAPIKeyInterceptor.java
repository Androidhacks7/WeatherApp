package com.androidhacks7.weatherapp.currentweather.data.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OpenWeatherServiceAPIKeyInterceptor implements Interceptor {

    public static final String API_KEY = "dc5f980cd4f2e04d76e7ed9c84010605";

    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpUrl url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("appid", API_KEY)
                .build();

        Request request = chain.request()
                .newBuilder()
                .url(url)
                .build();
        return chain.proceed(request);
    }
}
