package com.example.weatherwise.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherwise.R;
import com.example.weatherwise.activity.wisedetailActivity;
import com.example.weatherwise.model.Forecast;
import com.example.weatherwise.model.Weather;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ForcestAdapter extends RecyclerView.Adapter<ForcestAdapter.ForcestViewHolder> {

    public List<Weather> forcestList;

    public ForcestAdapter(List<Weather> forcestList) {
        this.forcestList = forcestList;
    }

    @NonNull
    @Override
    public ForcestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_today, parent, false);
        return new ForcestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForcestViewHolder holder, int position) {
        Weather forecast = forcestList.get(position);
        holder.cityTomorrow.setText(forecast.getCity());
        holder.countryTomorrow.setText(forecast.getCountry());
        setWeatherImage(forecast, holder.imageTomorrow);

        holder.imageTomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), wisedetailActivity.class);
                intent.putExtra("kota", forecast.getCity());
                intent.putExtra("temperatur", forecast.getForecasts().get(0).getTemperature());
                intent.putExtra("kelembapan", forecast.getForecasts().get(0).getHumidity());
                intent.putExtra("angin", forecast.getForecasts().get(0).getWind_speed());
                intent.putExtra("sekarang", forecast.getForecasts().get(0).getWeather_description());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return forcestList.size();
    }

    public static class ForcestViewHolder extends RecyclerView.ViewHolder {
        private TextView cityTomorrow;
        private TextView countryTomorrow;
        private ImageView imageTomorrow;

        public ForcestViewHolder(@NonNull View itemView) {
            super(itemView);
            cityTomorrow = itemView.findViewById(R.id.city);
            countryTomorrow = itemView.findViewById(R.id.country);
            imageTomorrow = itemView.findViewById(R.id.imageWeather);
        }
    }

    private void setWeatherImage(Weather weather, ImageView imageView) {
        if (weather.getForecasts() != null && !weather.getForecasts().isEmpty()) {
            String description = weather.getForecasts().get(0).getWeather_description().toLowerCase().trim();
            Log.d("WeatherDescription", "Description: " + description);

            int imageResId = getImageResourceId(description);
            Picasso.get().load(imageResId).into(imageView);
        } else {
            // Default image if no forecast is available
            Picasso.get().load(R.drawable.logoweatherwise).into(imageView);
        }
    }

    private int getImageResourceId(String description) {
        switch (description) {
            case "partly cloudy":
                return R.drawable.partlycloudy;
            case "sunny":
                return R.drawable.sunny;
            case "rain":
                return R.drawable.rain;
            case "cloudy":
                return R.drawable.cloudy;
            case "rainy":
                return R.drawable.rainy;
            case "clear sky":
                return R.drawable.clearsky;
            case "scattered clouds":
                return R.drawable.scatteredclouds;
            case "rain showers":
                return R.drawable.rainshowers;
            default:
                Log.d("WeatherDescription", "No match for description: " + description);
                return R.drawable.logoweatherwise; // Default image for unmatched descriptions
        }
    }
}
