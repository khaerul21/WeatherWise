package com.example.weatherwise.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherwise.R;
import com.example.weatherwise.activity.wisedetailActivity;
import com.example.weatherwise.model.Weather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    public List<Weather> weathersSearch;

    public SearchAdapter(List<Weather> weathersSearch) {
        this.weathersSearch = weathersSearch;
    }


    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {

        Weather search = weathersSearch.get(position);
        holder.kota_search.setText(search.getCity());
        holder.temperatur_search.setText(String.valueOf(search.getTemperature())+"°");
        setWeatherImage(search, holder.gambar_search);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), wisedetailActivity.class);
                intent.putExtra("kota", search.getCity());
                intent.putExtra("temperatur", search.getTemperature());
                intent.putExtra("kelembapan", search.getHumidity());
                intent.putExtra("angin", search.getWind_speed());
                intent.putExtra("sekarang", search.getWeather_description());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weathersSearch.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView kota_search;

        private TextView temperatur_search;
        private ImageView gambar_search;
//        private CardView card;


        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            kota_search = itemView.findViewById(R.id.kota_search);
            temperatur_search = itemView.findViewById(R.id.temperatur_search);
            gambar_search = itemView.findViewById(R.id.image_search);
//            card = itemView.findViewById(R.id.card);
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
