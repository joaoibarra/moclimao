package br.com.ibarra.moclimao.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.ibarra.moclimao.R;
import br.com.ibarra.moclimao.api.models.WeatherDailyItem;
import br.com.ibarra.moclimao.api.models.WeatherToday;
import br.com.ibarra.moclimao.ui.holders.WeatherHolder;

/**
 * Created by joaoibarra on 09/01/16.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder>{
    private static List<WeatherDailyItem> weatherDailyItems;
    private static WeatherToday weatherToday;

    public WeatherAdapter(WeatherToday weatherToday) {
       this.weatherToday = weatherToday;
    }

    public WeatherAdapter(List<WeatherDailyItem> weatherDailyItems) {
        this.weatherDailyItems = weatherDailyItems;
    }

    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (getItemCount() == 0 && weatherToday!=null) {
            v = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.weather_today_holder, parent, false);
        } else {
            v = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.weather_holder, parent, false);
        }
        return new WeatherHolder(v);
    }

    @Override
    public void onBindViewHolder(WeatherHolder holder, int position) {
        if(getItemCount() == 0 && weatherToday!=null){
            holder.getTextViewTemperature().setText(weatherToday.getMain().getTemperature());
            holder.getTextViewDescription().setText(weatherToday.getWeather().get(0).getDescription());
            holder.getTextViewHumidity().setText(weatherToday.getMain().getHumidity());
        }else {
            WeatherDailyItem weatherDailyItem = this.weatherDailyItems.get(position);
            holder.getTextViewDayTemperature().setText(weatherDailyItem.getDayTemperature().getDayTemperature());
            holder.getTextViewMaxTemperature().setText(weatherDailyItem.getDayTemperature().getMaxDailyTemperature());
            holder.getTextViewMinTemperature().setText(weatherDailyItem.getDayTemperature().getMinDailyTemperature());
        }
    }

    @Override
    public int getItemCount() {
        if(weatherDailyItems.size() == 0 && weatherToday!=null)
            return 1;

        return weatherDailyItems.size();
    }

}
