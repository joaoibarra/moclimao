package br.com.ibarra.moclimao.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoibarra on 09/01/16.
 */
public class City {
    private String id;
    private String name;

    @SerializedName("coord")
    private Coordinates coordinates;

    private String country;
    private String population;
}
