package com.geekbrains.weather;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class WeatherFragment extends Fragment {
    private TextView city_name, info, temperature;
    private Button setting_btn;
    public WeatherFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        city_name = getActivity().findViewById(R.id.city_name);
        info = getActivity().findViewById(R.id.info);
        temperature = getActivity().findViewById(R.id.temperature);
        setting_btn = getActivity().findViewById(R.id.setting_btn);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
