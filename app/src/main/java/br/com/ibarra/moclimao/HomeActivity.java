package br.com.ibarra.moclimao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.ibarra.moclimao.api.models.WeatherDaily;
import br.com.ibarra.moclimao.api.models.WeatherDailyItem;
import br.com.ibarra.moclimao.api.models.WeatherToday;
import br.com.ibarra.moclimao.api.service.OpenWeatherMapServiceImpl;
import br.com.ibarra.moclimao.ui.activities.BaseActivity;
import br.com.ibarra.moclimao.ui.adapters.WeatherAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements BaseActivity{
    @Bind(R.id.progressbar) LinearLayout progressbarLayout;
    @Bind(R.id.error) RelativeLayout errorLayout;
    @Bind(R.id.content) RelativeLayout contentLayout;
    @Bind(R.id.weather_daily_list) RecyclerView weatherDailyList;
    @Bind(R.id.temperature) TextView textViewTemperature;
    @Bind(R.id.description) TextView textViewDescription;
    @Bind(R.id.humidity) TextView textViewHumidity;

    //private WeatherAdapter weatherAdapter = new WeatherAdapter(new ArrayList<WeatherDailyItem>());
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layoutManager = new LinearLayoutManager(this);
        weatherDailyList.setLayoutManager(layoutManager);
        getWeather();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //weatherDailyList.setAdapter(weatherAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getWeather(){
        onLoadProgress();
        Call<WeatherToday> call = OpenWeatherMapServiceImpl.getInstance().getWeatherToday("Campo Grande", "metric");
        call.enqueue(new Callback<WeatherToday>() {
            @Override
            public void onResponse(Response<WeatherToday> response) {
                if (response.isSuccess()) {
                    WeatherToday weatherToday = response.body();
                    setLayoutValues(weatherToday);
                    /*onFinishProgress();
                    showContent();*/
                    getWeatherNextDays();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.fillInStackTrace();
                onFinishProgress();
                onFinishError();
            }
        });
    }

    public void getWeatherNextDays(){
        //onLoadProgress();
        Call<WeatherDaily> call = OpenWeatherMapServiceImpl.getInstance().getWeatherDaily("Campo Grande", "metric", "5");
        call.enqueue(new Callback<WeatherDaily>() {
            @Override
            public void onResponse(Response<WeatherDaily> response) {
                if (response.isSuccess()) {
                    WeatherDaily weatherDaily = response.body();
                    WeatherAdapter weatherAdapter = new WeatherAdapter(weatherDaily.getList());
                    weatherDailyList.setAdapter(weatherAdapter);
                    onFinishProgress();
                    showContent();
                }
            }

            @Override
            public void onFailure(Throwable t) {
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

    public void setLayoutValues(WeatherToday weatherToday){
        textViewTemperature.setText(weatherToday.getMain().getTemperature());
        textViewDescription.setText(weatherToday.getWeather().get(0).getDescription());
        textViewHumidity.setText(weatherToday.getMain().getHumidity());
    }
}
