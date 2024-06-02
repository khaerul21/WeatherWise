package com.example.weatherwise.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.weatherwise.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class wisedetailActivity extends AppCompatActivity {

    private TextView kota;
    private ImageView gambar;
    private TextView temperatur;
    private TextView humidity;
    private TextView kelembapan;
    private TextView kecepatan;
    private TextView angin;
    private TextView deskripsi;
    private TextView sekarang;
    private ProgressBar loadDetail;
    Handler handler = new Handler(Looper.getMainLooper());
    private LinearLayout linearLayout;

    Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wisedetail);

        kota = findViewById(R.id.kota);
        gambar = findViewById(R.id.gambar);
        temperatur = findViewById(R.id.temperatur);
        kelembapan = findViewById(R.id.kelembapan);
        kecepatan = findViewById(R.id.kecepatan);
        angin = findViewById(R.id.angin);
        deskripsi = findViewById(R.id.deskripsi);
        sekarang = findViewById(R.id.sekarang);
        loadDetail = findViewById(R.id.loaddetails);
        linearLayout = findViewById(R.id.lineardetails);


        Intent intent = getIntent();
        String thisKota = intent.getStringExtra("kota");
        Double thistemperatur = intent.getDoubleExtra("temperatur", -1);
        Integer thiskelembapan = intent.getIntExtra("kelembapan",-1);
        Double thisangin = intent.getDoubleExtra("angin",-1);
        String thissekarang = intent.getStringExtra("sekarang");

        kota.setText(thisKota);
        temperatur.setText(String.valueOf(thistemperatur)+"°");
        kelembapan.setText(String.valueOf(thiskelembapan));
        angin.setText(String.valueOf(thisangin));
        sekarang.setText(thissekarang);

        linearLayout.setVisibility(View.GONE);
        loadDetail.setVisibility(View.VISIBLE);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        linearLayout.setVisibility(View.VISIBLE);
                        loadDetail.setVisibility(View.GONE);
                    }
                });
            }
        });

        if (thissekarang.contains("Partly cloudy")) {
            gambar.setImageResource(R.drawable.partlycloudy);
        } else if (thissekarang.contains("Sunny")) {
            gambar.setImageResource(R.drawable.sunny);
        } else if (thissekarang.contains("Rain")) {
            gambar.setImageResource(R.drawable.rain);
        } else if (thissekarang.contains("Cloudy")) {
            gambar.setImageResource(R.drawable.cloudy);
        } else if (thissekarang.contains("Rainy")) {
            gambar.setImageResource(R.drawable.rainy);
        } else if (thissekarang.contains("Clear sky")) {
            gambar.setImageResource(R.drawable.clearsky);
        } else if (thissekarang.contains("Scattered clouds")) {
            gambar.setImageResource(R.drawable.scatteredclouds);
        } else if (thissekarang.contains("Rain showers")) {
            gambar.setImageResource(R.drawable.rainshowers);
        }


    }
}