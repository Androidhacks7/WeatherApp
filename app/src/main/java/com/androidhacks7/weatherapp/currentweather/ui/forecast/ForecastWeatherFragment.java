package com.androidhacks7.weatherapp.currentweather.ui.forecast;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.androidhacks7.weatherapp.R;
import com.androidhacks7.weatherapp.currentweather.data.db.entity.ForecastWeatherResponse;
import com.androidhacks7.weatherapp.currentweather.data.db.entity.List;
import com.androidhacks7.weatherapp.currentweather.ui.forecast.adapters.ForecastListAdapter;

@SuppressLint("ValidFragment")
public class ForecastWeatherFragment extends Fragment {

    private RecyclerView recyclerView;
    private AutoCompleteTextView city;
    private Button getWeather;

    private ForecastListAdapter forecastListAdapter;

    private ForecastWeatherViewModel forecastWeatherViewModel;
    private ForecastWeatherViewModelFactory forecastWeatherViewModelFactory;

    @SuppressLint("ValidFragment")
    public ForecastWeatherFragment(ForecastWeatherViewModelFactory forecastWeatherViewModelFactory) {
        this.forecastWeatherViewModelFactory = forecastWeatherViewModelFactory;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forecast_weather_list, container, false);
        recyclerView = view.findViewById(R.id.weather_selected_city);
        city = view.findViewById(R.id.city_search);
        getWeather = view.findViewById(R.id.get_weather);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        forecastWeatherViewModel = ViewModelProviders.of(this, forecastWeatherViewModelFactory)
                .get(ForecastWeatherViewModel.class);
        getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerSearch();
            }
        });
        city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                triggerSearch();
            }
        });
        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (forecastListAdapter != null && forecastListAdapter.getCount() != 0) {
                    forecastListAdapter.clear();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fetchRecents();
    }

    private void fetchRecents() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, forecastWeatherViewModel.getRecents());
        city.setAdapter(adapter);

    }

    private void triggerSearch() {
        final String text = city.getText().toString();
        forecastWeatherViewModel.initWeatherData(text);
        forecastWeatherViewModel.getForecastWeather(text).observe(getActivity(), new Observer<ForecastWeatherResponse>() {
            @Override
            public void onChanged(@Nullable ForecastWeatherResponse forecastWeatherResponse) {
                if (forecastWeatherResponse == null) {
                    return;
                }
                java.util.List<List> weatherInformation = forecastWeatherResponse.getWeatherList();
                forecastListAdapter = new ForecastListAdapter(weatherInformation);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(forecastListAdapter);
                recyclerView.invalidate();
                fetchRecents();
            }
        });
    }
}
