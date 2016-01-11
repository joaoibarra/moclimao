package br.com.ibarra.moclimao.ui.holders;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.ibarra.moclimao.R;
import br.com.ibarra.moclimao.api.models.WeatherDailyItem;
import br.com.ibarra.moclimao.ui.activities.WeatherDetailActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by joaoibarra on 09/01/16.
 */

public class WeatherHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{
    @Nullable
    @Bind(R.id.date) TextView textViewDate;
    @Nullable
    @Bind(R.id.max_temperature) TextView textViewMaxTemperature;
    @Nullable
    @Bind(R.id.min_temperature) TextView textViewMinTemperature;
    @Nullable
    @Bind(R.id.temperature) TextView textViewTemperature;
    @Nullable
    @Bind(R.id.unit) TextView textViewUnit;
    @Nullable
    @Bind(R.id.humidity) TextView textViewHumidity;
    @Nullable
    @Bind(R.id.description) TextView textViewDescription;
    @Nullable
    @Bind(R.id.image) ImageView image;

    WeatherDailyItem weatherDailyItem;

    public WeatherHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(view.getContext(), WeatherDetailActivity.class);
        EventBus.getDefault().postSticky(getWeatherDailyItem());
        view.getContext().startActivity(intent);
    }

    public TextView getTextViewDate() {
        return textViewDate;
    }

    public TextView getTextViewMaxTemperature() {
        return textViewMaxTemperature;
    }

    public TextView getTextViewMinTemperature() {
        return textViewMinTemperature;
    }

    public TextView getTextViewTemperature() {
        return textViewTemperature;
    }

    public TextView getTextViewUnit() {
        return textViewUnit;
    }

    public TextView getTextViewHumidity() {
        return textViewHumidity;
    }

    public TextView getTextViewDescription() {
        return textViewDescription;
    }

    public WeatherDailyItem getWeatherDailyItem() {
        return weatherDailyItem;
    }

    public void setWeatherDailyItem(WeatherDailyItem weatherDailyItem) {
        this.weatherDailyItem = weatherDailyItem;
    }

    @Nullable
    public ImageView getImage() {
        return image;
    }

    public void setImage(@Nullable ImageView image) {
        this.image = image;
    }
}
