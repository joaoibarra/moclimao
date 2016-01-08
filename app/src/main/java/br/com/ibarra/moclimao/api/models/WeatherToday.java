package br.com.ibarra.moclimao.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joaoibarra on 05/01/16.
 */
public class WeatherToday {
    @SerializedName("coord")
    private Coordinates coordinates;
    private List<Weather> weather;
    private String base;
    private Main main;
    private Rain rain;
    private Cloud clouds;
    private String dt;
    private Sys sys;
    private Long id;
    private String name;
    private int cod;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public Rain getRain() {
        return rain;
    }

    public Cloud getClouds() {
        return clouds;
    }

    public String getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }
}
