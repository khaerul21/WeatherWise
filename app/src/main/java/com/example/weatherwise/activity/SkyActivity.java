package com.example.weatherwise.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.weatherwise.R;

public class SkyActivity extends AppCompatActivity {
    private TextView textViewIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sky);

        textViewIntent = findViewById(R.id.intent);

        textViewIntent.setOnClickListener(v ->{
            Intent intent = new Intent(SkyActivity.this, MainActivity.class);
            startActivity(intent);
        });



    }
}