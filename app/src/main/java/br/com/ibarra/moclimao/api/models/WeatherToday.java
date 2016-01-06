package br.com.ibarra.moclimao.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoibarra on 05/01/16.
 */
public class WeatherToday {
    @SerializedName("coord")
    private Coordinates coordinates;
    private Weather weather;
    private String base;
    private Main main;
    private Rain rain;
    private Cloud clouds;
    private String dt;
    private Sys sys;
    private Long id;
    private String name;
    private int cod;
}
