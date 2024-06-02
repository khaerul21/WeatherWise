package com.example.weatherwise.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherwise.DataBaseHelper;
import com.example.weatherwise.R;
import com.example.weatherwise.adapter.MycityAdapter;


public class ProfilFragment extends Fragment {
    private RecyclerView rvMyCity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        rvMyCity = view.findViewById(R.id.cityRf);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());

        rvMyCity.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvMyCity.setAdapter(new MycityAdapter(dataBaseHelper.getMyCity(), getContext()));
        return view;
    }
}