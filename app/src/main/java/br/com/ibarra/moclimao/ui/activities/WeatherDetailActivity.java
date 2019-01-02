package br.com.ibarra.moclimao.ui.activities;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.com.ibarra.moclimao.R;
import br.com.ibarra.moclimao.api.models.WeatherDailyItem;
import br.com.ibarra.moclimao.util.Url;
import br.com.ibarra.moclimao.util.Util;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joaoibarra on 09/01/16.
 */
public class WeatherDetailActivity extends AppCompatActivity implements BaseActivity {
    @BindView(R.id.progressbar)
    LinearLayout progressbarLayout;
    @BindView(R.id.error)
    RelativeLayout errorLayout;
    @BindView(R.id.content)
    NestedScrollView contentLayout;
    @BindView(R.id.date)
    TextView textViewDate;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.max_temperature)
    TextView textViewMaxTemperature;
    @BindView(R.id.min_temperature)
    TextView textViewMinTemperature;
    @BindView(R.id.humidity)
    TextView textViewHumidity;
    @BindView(R.id.morning)
    TextView textViewMorning;
    @BindView(R.id.evening)
    TextView textViewEvening;
    @BindView(R.id.night)
    TextView textViewNight;

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

    private void setLayoutValues() {
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
        textViewDate.setText(Util.dateToString(weatherDailyItem.getDt()));
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
