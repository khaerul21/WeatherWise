package com.example.weatherwise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherwise.DataBaseHelper;
import com.example.weatherwise.R;
import com.example.weatherwise.model.Weather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MycityAdapter extends RecyclerView.Adapter<MycityAdapter.MycityViewHolder> {
    public ArrayList<Weather> MycityList;
    public Context context;

    public MycityAdapter(ArrayList<Weather> mycityList, Context context) {
        this.MycityList = mycityList;
        this.context = context;

    }



    @Override
    public MycityAdapter.MycityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mycity, parent, false);
        return new MycityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MycityAdapter.MycityViewHolder holder, int position) {
        Weather mycitycity = MycityList.get(position);
        holder.mycitymu.setText(mycitycity.getCity());
        holder.mycitytemperatur.setText(String.valueOf(mycitycity.getTemperature())+"°");

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        int weather_id = dataBaseHelper.getWeatherIdFromMyCityToUseItOnMyCityAdapterWhichShowedOnMyCityFragment(mycitycity.getId());

//        setWeatherImage(weather_id, holder.imagemycity);


    }

    @Override
    public int getItemCount() {
        return MycityList.size();

    }

    public static class MycityViewHolder extends RecyclerView.ViewHolder {

        private TextView mycitymu;
        private TextView mycitytemperatur;
        private ImageView imagemycity;

        public MycityViewHolder(@NonNull View itemView) {
            super(itemView);
            mycitymu = itemView.findViewById(R.id.mycitymu);
            mycitytemperatur = itemView.findViewById(R.id.mycitytemperatur);
            imagemycity = itemView.findViewById(R.id.mycityimage);
        }
    }

//    private void setWeatherImage(int weather_id, ImageView imageView) {
//        if (weather_id.getWeather_description().contains("Partly cloudy")) {
//            Picasso.get().load(R.drawable.partlycloudy).into(imageView);
//        } else if (weather_id.getWeather_description().contains("Sunny")) {
//            Picasso.get().load(R.drawable.sunny).into(imageView);
//        } else if (weather_id.getWeather_description().contains("Rain")) {
//            Picasso.get().load(R.drawable.rain).into(imageView);
//        } else if (weather_id.getWeather_description().contains("Cloudy")) {
//            Picasso.get().load(R.drawable.cloudy).into(imageView);
//        } else if (weather_id.getWeather_description().contains("Rainy")) {
//            Picasso.get().load(R.drawable.rainy).into(imageView);
//        } else if (weather_id.getWeather_description().contains("Clear sky")) {
//            Picasso.get().load(R.drawable.clearsky).into(imageView);
//        } else if (weather_id.getWeather_description().contains("Scattered clouds")) {
//            Picasso.get().load(R.drawable.scatteredclouds).into(imageView);
//        } else if (weather_id.getWeather_description().contains("Rain showers")) {
//            Picasso.get().load(R.drawable.rainshowers).into(imageView);
//        }
//
//    }
}
