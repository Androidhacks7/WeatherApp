package com.androidhacks7.weatherapp.currentweather.ui.forecast.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.androidhacks7.weatherapp.BR;
import com.androidhacks7.weatherapp.R;
import com.androidhacks7.weatherapp.currentweather.data.db.entity.List;


public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ForecastItemHolder> {

    private java.util.List<List> forecastWeather;

    @NonNull
    @Override
    public ForecastItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.forecast_list_row, parent, false);

        return new ForecastItemHolder(binding);
    }

    public ForecastListAdapter(java.util.List<List> forecastWeather) {
        this.forecastWeather = forecastWeather;
    }


    public int getCount() {
        return forecastWeather.size();
    }

    public void clear() {
        this.forecastWeather.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastItemHolder viewHolder, int position) {
        viewHolder.binding.setVariable(BR.weather, forecastWeather.get(position));
        viewHolder.binding.setVariable(BR.weather_detail, forecastWeather.get(position).getMain());
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return forecastWeather.size();
    }

    public class ForecastItemHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public ForecastItemHolder(final ViewDataBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
