package br.com.ibarra.moclimao.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoibarra on 09/01/16.
 */
public class DayTemperature {
    @SerializedName("day")
    private String dayTemperature;

    @SerializedName("min")
    private String minDailyTemperature;

    @SerializedName("max")
    private String maxDailyTemperature;

    @SerializedName("night")
    private String nightTemperature;

    @SerializedName("eve")
    private String eveningTemperature;

    @SerializedName("morn")
    private String morningTemperature;

    public String getDayTemperature() {
        return dayTemperature;
    }

    public String getMinDailyTemperature() {
        return minDailyTemperature;
    }

    public String getMaxDailyTemperature() {
        return maxDailyTemperature;
    }

    public String getNightTemperature() {
        return nightTemperature;
    }

    public String getEveningTemperature() {
        return eveningTemperature;
    }

    public String getMorningTemperature() {
        return morningTemperature;
    }
}
