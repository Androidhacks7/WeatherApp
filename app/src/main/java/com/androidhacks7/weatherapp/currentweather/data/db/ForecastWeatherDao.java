package com.androidhacks7.weatherapp.currentweather.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.androidhacks7.weatherapp.currentweather.data.db.entity.ForecastWeatherResponse;

import java.util.List;

@Dao
public interface ForecastWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(ForecastWeatherResponse forecastWeatherResponse);

    @Query("SELECT COUNT(*) FROM weather where name LIKE :cityName")
    Integer isCityPresent(String cityName);

    @Query("SELECT * FROM weather where name LIKE :cityName")
    LiveData<ForecastWeatherResponse> getWeatherInformation(String cityName);

    @Query("SELECT cityName FROM weather")
    List<String> getRecents();
}
