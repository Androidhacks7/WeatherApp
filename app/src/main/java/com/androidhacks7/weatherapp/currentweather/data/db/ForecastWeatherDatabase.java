package com.androidhacks7.weatherapp.currentweather.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.androidhacks7.weatherapp.currentweather.data.db.entity.ForecastWeatherResponse;

@Database(entities = {ForecastWeatherResponse.class}, version = 1)
public abstract class ForecastWeatherDatabase extends RoomDatabase {

    public abstract ForecastWeatherDao currentWeatherDao();

    private static volatile ForecastWeatherDatabase instance = null;
    private static Object lock = new Object();

    private static ForecastWeatherDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context,
                ForecastWeatherDatabase.class, "forecast.db")
                .allowMainThreadQueries()
                .build();
    }

    public static ForecastWeatherDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            synchronized (lock) {
                instance = buildDatabase(context);
                return instance;
            }
        }
    }
}
