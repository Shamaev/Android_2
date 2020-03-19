package com.geekbrains.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    public OnCityItemClickListener listener;

    private String[] cities;
    public CityAdapter (String[] arr, OnCityItemClickListener listener) {
        cities = arr;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        holder.setLable(cities[position]);
    }

    @Override
    public int getItemCount() {
        return cities.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label);
            label.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnClick(label.getText().toString());
                }
            });
        }

        void setLable (String text) {
            label.setText(text);
        }
    }
}
