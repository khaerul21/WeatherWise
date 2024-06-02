package com.example.weatherwise.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherwise.DataBaseHelper;
import com.example.weatherwise.R;
import com.example.weatherwise.activity.wisedetailActivity;
import com.example.weatherwise.model.Weather;
import com.squareup.picasso.Picasso;

import java.sql.DataTruncation;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {
    public List<Weather> weatherList;
    public WeatherAdapter(List<Weather> weatherList) {this.weatherList = weatherList; }

    public WeatherAdapter.WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_today, parent, false);
        return new WeatherHolder(view);
    }

    public void onBindViewHolder(@NonNull WeatherAdapter.WeatherHolder holder, int position) {
        Weather weathers = weatherList.get(position);
        holder.cities.setText(weathers.getCity());
        holder.country.setText(weathers.getCountry());
        setWeatherImage(weathers, holder.gambar);

        holder.gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), wisedetailActivity.class);
                intent.putExtra("kota", weathers.getCity());
                intent.putExtra("temperatur", weathers.getTemperature());
                intent.putExtra("kelembapan", weathers.getHumidity());
                intent.putExtra("angin", weathers.getWind_speed());
                intent.putExtra("sekarang", weathers.getWeather_description());
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.favorite.setOnClickListener(v -> {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(v.getContext());
            dataBaseHelper.insertMyCity(weathers);
            Toast.makeText(v.getContext(), "City Added", Toast.LENGTH_SHORT).show();

        });

    }

    public int getItemCount() {
        return weatherList.size();
    }

    public static class WeatherHolder extends RecyclerView.ViewHolder {
        private TextView cities;
        private TextView country;
        private ImageView gambar;
        private ImageView favorite;

        public WeatherHolder(@NonNull View itemView) {
            super(itemView);
            cities = itemView.findViewById(R.id.city);
            country = itemView.findViewById(R.id.country);
            gambar = itemView.findViewById(R.id.imageWeather);
            favorite = itemView.findViewById(R.id.addmycity);

        }
    }

    private void setWeatherImage(Weather weather, ImageView imageView) {
        if (weather.getWeather_description().contains("Partly cloudy")) {
            Picasso.get().load(R.drawable.partlycloudy).into(imageView);
        } else if (weather.getWeather_description().contains("Sunny")) {
            Picasso.get().load(R.drawable.sunny).into(imageView);
        } else if (weather.getWeather_description().contains("Rain")) {
            Picasso.get().load(R.drawable.rain).into(imageView);
        } else if (weather.getWeather_description().contains("Cloudy")) {
            Picasso.get().load(R.drawable.cloudy).into(imageView);
        } else if (weather.getWeather_description().contains("Rainy")) {
            Picasso.get().load(R.drawable.rainy).into(imageView);
        } else if (weather.getWeather_description().contains("Clear sky")) {
            Picasso.get().load(R.drawable.clearsky).into(imageView);
        } else if (weather.getWeather_description().contains("Scattered clouds")) {
            Picasso.get().load(R.drawable.scatteredclouds).into(imageView);
        } else if (weather.getWeather_description().contains("Rain showers")) {
            Picasso.get().load(R.drawable.rainshowers).into(imageView);
        }

    }
}
