package com.example.weatherwise.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.weatherwise.R;
import com.example.weatherwise.adapter.ForcestAdapter;
import com.example.weatherwise.api.RetrofitClient;
import com.example.weatherwise.api.WeatherApi;
import com.example.weatherwise.model.Weather;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Response;

public class PerHourFragment extends Fragment {
    private RecyclerView recyclerViewTomorrow;
    private WeatherApi weatherApi;
    private ForcestAdapter forcestAdapter;
    private ProgressBar loadtomorrow;
    Executor executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_per_hour, container, false);
        weatherApi = RetrofitClient.getClient().create(WeatherApi.class);
        recyclerViewTomorrow = view.findViewById(R.id.tomorrowFv);
        loadtomorrow = view.findViewById(R.id.loadtomorrow);
        recyclerViewTomorrow.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Tampilkan ProgressBar
        loadtomorrow.setVisibility(View.VISIBLE);

        executor.execute(() -> {
            try {
                Call<List<Weather>> call = weatherApi.getWeatherList();
                Response<List<Weather>> response = call.execute();

                if (response.isSuccessful()) {
                    List<Weather> forcest = response.body();

                    handler.post(() -> {
                        forcestAdapter = new ForcestAdapter(forcest);
                        recyclerViewTomorrow.setAdapter(forcestAdapter);
                        loadtomorrow.setVisibility(View.GONE); // Sembunyikan ProgressBar
                    });
                } else {
                    Log.d("WeatherAPI", "Response not successful: " + response.message());
                    handler.post(() -> loadtomorrow.setVisibility(View.GONE)); // Sembunyikan ProgressBar
                }
            } catch (Exception e) {
                Log.d("WeatherAPI", "Request failed: " + e.getMessage());
                handler.post(() -> loadtomorrow.setVisibility(View.GONE)); // Sembunyikan ProgressBar
            }
        });

        return view;
    }
}
