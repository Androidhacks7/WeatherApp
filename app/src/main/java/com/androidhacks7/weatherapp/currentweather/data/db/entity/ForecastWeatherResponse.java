
package com.androidhacks7.weatherapp.currentweather.data.db.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.androidhacks7.weatherapp.currentweather.data.db.WeatherListConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity(tableName = "weather")
public class ForecastWeatherResponse {
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    @NonNull
//    @PrimaryKey(autoGenerate = false)
    private Integer cnt;
    @SerializedName("list")
    @Expose
    @NonNull
    @TypeConverters(WeatherListConverter.class)
    private java.util.ArrayList<List> weatherList = null;
    @SerializedName("city")
    @Expose
    @NonNull
    @Embedded
    private City city;
    @Expose
    @NonNull
    @PrimaryKey
    private String cityName;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @NonNull
    public ArrayList<List> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(@NonNull ArrayList<List> weatherList) {
        this.weatherList = weatherList;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @NonNull
    public String getCityName() {
        return cityName;
    }

    public void setCityName(@NonNull String cityName) {
        this.cityName = cityName;
    }
}
