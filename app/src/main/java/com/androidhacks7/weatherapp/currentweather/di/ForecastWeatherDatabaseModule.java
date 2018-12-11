package com.androidhacks7.weatherapp.currentweather.di;

import android.content.Context;

import com.androidhacks7.weatherapp.currentweather.data.db.ForecastWeatherDao;
import com.androidhacks7.weatherapp.currentweather.data.db.ForecastWeatherDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class ForecastWeatherDatabaseModule {

    @Provides @Singleton
    public ForecastWeatherDatabase getDataBase(Context context) {
        ForecastWeatherDatabase forecastWeatherDatabase = ForecastWeatherDatabase.getInstance(context);
        return forecastWeatherDatabase;
    }

    @Provides
    public ForecastWeatherDao getDAO(Context context) {
        return getDataBase(context).currentWeatherDao();
    }

}
