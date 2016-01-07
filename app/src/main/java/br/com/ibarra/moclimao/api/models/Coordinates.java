package br.com.ibarra.moclimao.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoibarra on 05/01/16.
 */
public class Coordinates {
    @SerializedName("lat")
    private String latitude;
    @SerializedName("lon")
    private String longitude;
}
