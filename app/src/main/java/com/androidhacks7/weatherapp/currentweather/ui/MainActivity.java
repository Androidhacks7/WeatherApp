package com.androidhacks7.weatherapp.currentweather.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.androidhacks7.weatherapp.R;
import com.androidhacks7.weatherapp.currentweather.di.ContextModule;
import com.androidhacks7.weatherapp.currentweather.di.DaggerForecastWeatherViewModelFactoryComponent;
import com.androidhacks7.weatherapp.currentweather.di.ForecastWeatherDatabaseModule;
import com.androidhacks7.weatherapp.currentweather.di.ForecastWeatherRepositoryModule;
import com.androidhacks7.weatherapp.currentweather.di.ForecastWeatherViewModelFactoryComponent;
import com.androidhacks7.weatherapp.currentweather.di.NetworkModule;
import com.androidhacks7.weatherapp.currentweather.di.OpenWeatherServiceModule;
import com.androidhacks7.weatherapp.currentweather.di.WeatherNetworkDataSourceModule;
import com.androidhacks7.weatherapp.currentweather.ui.forecast.ForecastWeatherFragment;
import com.androidhacks7.weatherapp.currentweather.ui.forecast.ForecastWeatherViewModelFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDependencies();
    }

    private void initDependencies() {
        ForecastWeatherViewModelFactoryComponent forecastWeatherViewModelFactoryComponent =
                DaggerForecastWeatherViewModelFactoryComponent.builder().contextModule(new ContextModule(this))
                .forecastWeatherDatabaseModule(new ForecastWeatherDatabaseModule())
                        .openWeatherServiceModule(new OpenWeatherServiceModule())
                        .weatherNetworkDataSourceModule(new WeatherNetworkDataSourceModule())
                        .forecastWeatherRepositoryModule(new ForecastWeatherRepositoryModule())
                        .networkModule(new NetworkModule()).build();
        ForecastWeatherViewModelFactory forecastWeatherViewModelFactory = forecastWeatherViewModelFactoryComponent.getForecastViewModelFactory();
        ForecastWeatherFragment forecastWeatherFragment = new ForecastWeatherFragment(forecastWeatherViewModelFactory);
        getSupportFragmentManager().beginTransaction().add(R.id.main_holder, forecastWeatherFragment).commitAllowingStateLoss();
    }


}
