package com.example.weatherwise.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.weatherwise.R;
import com.example.weatherwise.adapter.SearchAdapter;
import com.example.weatherwise.api.RetrofitClient;
import com.example.weatherwise.api.WeatherApi;
import com.example.weatherwise.model.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SearchFragment extends Fragment {
    private WeatherApi weatherApi;
    private RecyclerView recyclerViewSearch;
    private EditText searchWeather;
    private ArrayList <Weather> allWeathers;
    private ArrayList <Weather> filteredWeathers;
    private SearchAdapter searchAdapter;
    private ProgressBar searchload;
    Executor executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        searchWeather = view.findViewById(R.id.searchWeatherByCity);
        searchload = view.findViewById(R.id.searchload);
        weatherApi = RetrofitClient.getClient().create(WeatherApi.class);
        recyclerViewSearch = view.findViewById(R.id.tampilan);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));


        allWeathers = new ArrayList<>();
        filteredWeathers = new ArrayList<>();
        searchAdapter = new SearchAdapter(filteredWeathers);
        recyclerViewSearch.setAdapter(searchAdapter);

        recyclerViewSearch.setVisibility(View.GONE);
        searchload.setVisibility(View.GONE);


        searchWeather.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        fetchWeathers();

        return view;

    }

    private void fetchWeathers() {
        Call<List<Weather>> call = weatherApi.getWeatherList();

        call.enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                if (response.isSuccessful()) {
                    allWeathers = new ArrayList<>(response.body());
                    filteredWeathers.clear();
                    filteredWeathers.addAll(allWeathers);
                    searchAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void filter(String text) {
        filteredWeathers.clear();
        if (text.isEmpty()) {
            recyclerViewSearch.setVisibility(View.GONE);
        } else {
            searchload.setVisibility(View.VISIBLE);
            recyclerViewSearch.setVisibility(View.GONE);

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (Weather weather : allWeathers) {
                            if (weather.getCity().toLowerCase().contains(text.toLowerCase())) {
                                filteredWeathers.add(weather);
                            }
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                searchload.setVisibility(View.GONE);
                                recyclerViewSearch.setVisibility(View.VISIBLE);
                                searchAdapter.notifyDataSetChanged();
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}