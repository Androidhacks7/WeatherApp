package com.androidhacks7.weatherapp.currentweather.data.db;

import android.arch.persistence.room.TypeConverter;

import com.androidhacks7.weatherapp.currentweather.data.db.entity.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class WeatherListConverter {
    @TypeConverter
    public static ArrayList<List> fromString(String value) {
        if (value == null) {
            return new ArrayList<>();
        }
        Type listType = new TypeToken<ArrayList<List>>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<List> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
