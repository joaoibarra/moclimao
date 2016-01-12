package br.com.ibarra.moclimao.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

import br.com.ibarra.moclimao.R;
import br.com.ibarra.moclimao.api.models.WeatherDailyItem;
import br.com.ibarra.moclimao.util.Url;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by joaoibarra on 09/01/16.
 */
public class WeatherDetailActivity extends AppCompatActivity implements BaseActivity{
    @Bind(R.id.progressbar) LinearLayout progressbarLayout;
    @Bind(R.id.error) RelativeLayout errorLayout;
    @Bind(R.id.content) NestedScrollView contentLayout;
    @Bind(R.id.date) TextView textViewDate;
    @Bind(R.id.image) ImageView image;
    @Bind(R.id.max_temperature) TextView textViewMaxTemperature;
    @Bind(R.id.min_temperature) TextView textViewMinTemperature;
    @Bind(R.id.humidity) TextView textViewHumidity;
    @Bind(R.id.morning) TextView textViewMorning;
    @Bind(R.id.evening) TextView textViewEvening;
    @Bind(R.id.night) TextView textViewNight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setLayoutValues();
    }

    private void setLayoutValues(){
        WeatherDailyItem weatherDailyItem = EventBus.getDefault().removeStickyEvent(WeatherDailyItem.class);
        textViewMaxTemperature.setText(weatherDailyItem.getDayTemperature().getMaxDailyTemperature());
        textViewMinTemperature.setText(weatherDailyItem.getDayTemperature().getMinDailyTemperature());
        textViewHumidity.setText(weatherDailyItem.getHumidity());
        textViewMorning.setText(weatherDailyItem.getDayTemperature().getMorningTemperature());
        textViewEvening.setText(weatherDailyItem.getDayTemperature().getEveningTemperature());
        textViewNight.setText(weatherDailyItem.getDayTemperature().getNightTemperature());

        Picasso.with(this)
                .load(Url.IMAGE + weatherDailyItem.getWeather().get(0).getIcon() + ".png")
                .into(image);
        textViewDate.setText(dateToString(weatherDailyItem.getDt()));
    }

    private String dateToString(String dateString){
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis((long) Integer.parseInt(dateString) * 1000);
       return date.get(Calendar.DAY_OF_MONTH) + "/" + String.format("%02d", date.get(Calendar.MONTH) + 1);
    }

    @Override
    public void onLoadProgress() {
        hideError();
        hideContent();
        progressbarLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishProgress() {
        progressbarLayout.setVisibility(View.GONE);
    }

    @Override
    public void onFinishError() {
        hideContent();
        errorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        errorLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideContent() {
        contentLayout.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        contentLayout.setVisibility(View.VISIBLE);
    }
}
