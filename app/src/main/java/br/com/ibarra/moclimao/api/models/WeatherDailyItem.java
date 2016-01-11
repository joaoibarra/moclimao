package br.com.ibarra.moclimao.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joaoibarra on 09/01/16.
 */
public class WeatherDailyItem {
    private String dt;

    @SerializedName("temp")
    private DayTemperature dayTemperature;

    private String pressure;

    private String humidity;

    private List<Weather> weather;

    private String speed;

    @SerializedName("deg")
    private String degrees;

    private String clouds;

    public String getDt() {
        return dt;
    }

    public DayTemperature getDayTemperature() {
        return dayTemperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getSpeed() {
        return speed;
    }

    public String getDegrees() {
        return degrees;
    }

    public String getClouds() {
        return clouds;
    }
}
