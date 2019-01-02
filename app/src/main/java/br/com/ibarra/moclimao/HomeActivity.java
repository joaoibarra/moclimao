package br.com.ibarra.moclimao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import br.com.ibarra.moclimao.api.models.Configuration;
import br.com.ibarra.moclimao.api.models.WeatherDaily;
import br.com.ibarra.moclimao.api.models.WeatherToday;
import br.com.ibarra.moclimao.api.service.OpenWeatherMapServiceImpl;
import br.com.ibarra.moclimao.ui.activities.BaseActivity;
import br.com.ibarra.moclimao.ui.activities.ConfigurationActivity;
import br.com.ibarra.moclimao.ui.adapters.WeatherAdapter;
import br.com.ibarra.moclimao.util.Url;
import br.com.ibarra.moclimao.util.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements BaseActivity {
    @BindView(R.id.progressbar)
    LinearLayout progressbarLayout;
    @BindView(R.id.error)
    RelativeLayout errorLayout;
    @BindView(R.id.content)
    LinearLayout contentLayout;
    @BindView(R.id.weather_daily_list)
    RecyclerView weatherDailyList;
    @BindView(R.id.temperature)
    TextView textViewTemperature;
    @BindView(R.id.description)
    TextView textViewDescription;
    @BindView(R.id.humidity)
    TextView textViewHumidity;
    @BindView(R.id.unit)
    TextView textViewUnit;
    @BindView(R.id.image)
    ImageView image;

    private LinearLayoutManager layoutManager;
    private Toolbar toolbar;
    Configuration configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        configuration = new Configuration(HomeActivity.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layoutManager = new LinearLayoutManager(this);
        weatherDailyList.setLayoutManager(layoutManager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ConfigurationActivity.class);
                startActivity(intent);
            }
        });
        setLayoutValues();
    }

    public void getWeather() {
        onLoadProgress();
        Call<WeatherToday> call = OpenWeatherMapServiceImpl.getInstance().getWeatherToday(configuration.getCity(), configuration.getUnitToString());
        call.enqueue(new Callback<WeatherToday>() {
            @Override
            public void onResponse(Call<WeatherToday> call, Response<WeatherToday> response) {
                if (response.isSuccessful()) {
                    WeatherToday weatherToday = response.body();
                    setLayoutValues(weatherToday);
                    getWeatherNextDays();
                }
            }

            @Override
            public void onFailure(Call<WeatherToday> call, Throwable t) {
                t.fillInStackTrace();
                onFinishProgress();
                onFinishError();
            }
        });
    }

    public void getWeatherNextDays() {
        Call<WeatherDaily> call = OpenWeatherMapServiceImpl.getInstance().getWeatherDaily(configuration.getCity(), configuration.getUnitToString(), "5");
        call.enqueue(new Callback<WeatherDaily>() {
            @Override
            public void onResponse(Call<WeatherDaily> call, Response<WeatherDaily> response) {
                if (response.isSuccessful()) {
                    WeatherDaily weatherDaily = response.body();
                    WeatherAdapter weatherAdapter = new WeatherAdapter(weatherDaily.getList());
                    weatherDailyList.setAdapter(weatherAdapter);
                    onFinishProgress();
                    showContent();
                }
            }

            @Override
            public void onFailure(Call<WeatherDaily> call, Throwable t) {
                t.fillInStackTrace();
                onFinishProgress();
                onFinishError();
            }
        });
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

    public void setLayoutValues(WeatherToday weatherToday) {
        Picasso.with(this)
                .load(Url.IMAGE + weatherToday.getWeather().get(0).getIcon() + ".png")
                .into(image);
        textViewTemperature.setText(Util.temperatureDoubleToString(weatherToday.getMain().getTemperature()));
        textViewUnit.setText(configuration.getUnitAbbreviation());
        textViewDescription.setText(weatherToday.getWeather().get(0).getDescription());
        textViewHumidity.setText(weatherToday.getMain().getHumidity() + "%");
    }

    public void setLayoutValues() {
        getSupportActionBar().setTitle(configuration.getCity());
        getWeather();
    }
}
