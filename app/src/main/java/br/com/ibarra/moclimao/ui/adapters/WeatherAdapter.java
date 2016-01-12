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
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis((long) Integer.parseInt(weatherDailyItem.getDt()) * 1000);

        holder.getTextViewDate().setText(date.get(Calendar.DAY_OF_MONTH)+"/" + String.format("%02d", date.get(Calendar.MONTH)+1));

        int max = (int) Double.parseDouble(weatherDailyItem.getDayTemperature().getMaxDailyTemperature());
        int min = (int) Double.parseDouble(weatherDailyItem.getDayTemperature().getMinDailyTemperature());
        holder.getTextViewMaxTemperature().setText(Integer.toString(max));
        holder.getTextViewMinTemperature().setText(Integer.toString(min));
        holder.setWeatherDailyItem(weatherDailyItem);
    }

    @Override
    public int getItemCount() {
        return weatherDailyItems.size();
    }

}
