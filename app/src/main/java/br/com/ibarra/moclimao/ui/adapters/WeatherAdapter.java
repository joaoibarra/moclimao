package br.com.ibarra.moclimao.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import br.com.ibarra.moclimao.R;
import br.com.ibarra.moclimao.api.models.WeatherDailyItem;
import br.com.ibarra.moclimao.api.models.WeatherToday;
import br.com.ibarra.moclimao.ui.holders.WeatherHolder;
import br.com.ibarra.moclimao.util.Url;
import br.com.ibarra.moclimao.util.Util;

/**
 * Created by joaoibarra on 09/01/16.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder>{
    private static List<WeatherDailyItem> weatherDailyItems;

    public WeatherAdapter(List<WeatherDailyItem> weatherDailyItems) {
        this.weatherDailyItems = weatherDailyItems;
    }

    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.weather_holder, parent, false);
        return new WeatherHolder(v);
    }

    @Override
    public void onBindViewHolder(WeatherHolder holder, int position) {
        WeatherDailyItem weatherDailyItem = this.weatherDailyItems.get(position);
        Picasso.with(holder.getImage().getContext())
                .load(Url.IMAGE + weatherDailyItem.getWeather().get(0).getIcon() + ".png")
                .into(holder.getImage());

        holder.getTextViewDate().setText(Util.dateToString(weatherDailyItem.getDt()));

        holder.getTextViewMaxTemperature().setText(Util.temperatureDoubleToString(weatherDailyItem.getDayTemperature().getMaxDailyTemperature()));
        holder.getTextViewMinTemperature().setText(Util.temperatureDoubleToString(weatherDailyItem.getDayTemperature().getMinDailyTemperature()));
        holder.setWeatherDailyItem(weatherDailyItem);
    }

    @Override
    public int getItemCount() {
        return weatherDailyItems.size();
    }

}
