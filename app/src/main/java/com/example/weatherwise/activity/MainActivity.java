package com.example.weatherwise.activity;

import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.weatherwise.R;
import com.example.weatherwise.fragment.HomeFragment;
import com.example.weatherwise.fragment.PerHourFragment;
import com.example.weatherwise.fragment.ProfilFragment;
import com.example.weatherwise.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frame;
    private BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        frame = findViewById(R.id.frame);
        bottomNavigationView = findViewById(R.id.bottom);
        homeFragment = new HomeFragment();

    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int ItemId = menuItem.getItemId();

            if (ItemId == R.id.HariIni){
                loadFragment(new HomeFragment(),false);
            } else if (ItemId == R.id.Tomorrow) {
                loadFragment(new PerHourFragment(),false);
            } else if (ItemId == R.id.Profil) {
                loadFragment(new ProfilFragment(),false);
            } else if (ItemId == R.id.Search){
                loadFragment(new SearchFragment(),false);
            }
            return true;

        }
    });

        loadFragment(new HomeFragment(),true);

    }

    private void loadFragment(Fragment fragment, boolean isAppOpen){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isAppOpen){
            fragmentTransaction.add(R.id.frame, fragment);

        }
        else {
            fragmentTransaction.replace(R.id.frame, fragment);

        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, homeFragment).commit();
    }
}