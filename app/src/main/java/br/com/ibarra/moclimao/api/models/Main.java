package br.com.ibarra.moclimao.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoibarra on 05/01/16.
 */
public class Main {
    @SerializedName("temp")
    private String temperature;

    private String humidity;

    @SerializedName("temp_min")
    private String minimumTemperature;

    @SerializedName("temp_max")
    private String maximumTemperature;
}
