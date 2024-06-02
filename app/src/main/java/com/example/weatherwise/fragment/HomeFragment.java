package com.example.weatherwise.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.weatherwise.R;
import com.example.weatherwise.adapter.WeatherAdapter;
import com.example.weatherwise.api.RetrofitClient;
import com.example.weatherwise.api.WeatherApi;
import com.example.weatherwise.model.Weather;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private WeatherApi weatherApi;
    private WeatherAdapter weatherAdapter;
    private ProgressBar loadhome;
    Executor executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        weatherApi = RetrofitClient.getClient().create(WeatherApi.class);
        recyclerView = view.findViewById(R.id.rv_weather);
        loadhome = view.findViewById(R.id.loadhome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        // Tampilkan ProgressBar
        loadhome.setVisibility(View.VISIBLE);

        executor.execute(() -> {
            try {
                Call<List<Weather>> call = weatherApi.getWeatherList();
                Response<List<Weather>> response = call.execute();

                if (response.isSuccessful()) {
                    List<Weather> weathers = response.body();
                    handler.post(() -> {
                        weatherAdapter = new WeatherAdapter(weathers);
                        recyclerView.setAdapter(weatherAdapter);
                        loadhome.setVisibility(View.GONE); // Sembunyikan ProgressBar
                    });
                } else {
                    handler.post(() -> {
                        System.out.println(response.message());
                        loadhome.setVisibility(View.GONE); // Sembunyikan ProgressBar
                    });
                }
            } catch (Exception e) {
                handler.post(() -> {
                    System.out.println(e.getMessage());
                    loadhome.setVisibility(View.GONE); // Sembunyikan ProgressBar
                });
            }
        });

        return view;
    }
}
