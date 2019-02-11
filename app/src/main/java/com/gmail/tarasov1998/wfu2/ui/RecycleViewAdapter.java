package com.gmail.tarasov1998.wfu2.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.tarasov1998.wfu2.R;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private List<String> temperData;
    private List<Integer> weatherData;
    private List<String> timeData;
    private LayoutInflater mInflater;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView temper, time;
        ImageView weatherIcon;

        ViewHolder(View itemView) {
            super(itemView);
            temper = itemView.findViewById(R.id.tempShowWeather);
            weatherIcon = itemView.findViewById(R.id.weatherIconShowWeather);
            time = itemView.findViewById(R.id.timeShowWeather);

        }

    }


    RecycleViewAdapter(Context context, List<String> temper, List<Integer> weatherIcon, List<String> time) {
        this.mInflater = LayoutInflater.from(context);
        this.temperData = temper;
        this.weatherData = weatherIcon;
        this.timeData = time;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.weather_view, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String tem = temperData.get(position);
        holder.temper.setText(tem);
        String tim = timeData.get(position);
        holder.time.setText(tim);
        Integer wthIc = weatherData.get(position);
        holder.weatherIcon.setImageResource(wthIc);
    }

    @Override
    public int getItemCount() {
        return 5;

    }


}
