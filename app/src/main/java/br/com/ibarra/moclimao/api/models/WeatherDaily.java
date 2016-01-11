package br.com.ibarra.moclimao.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joaoibarra on 09/01/16.
 */
public class WeatherDaily {
    private City city;
    private String cod;
    private String message;
    @SerializedName("cnt")
    private String count;
    List<WeatherDailyItem> list;

    public City getCity() {
        return city;
    }

    public String getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }

    public String getCount() {
        return count;
    }

    public List<WeatherDailyItem> getList() {
        return list;
    }
}
