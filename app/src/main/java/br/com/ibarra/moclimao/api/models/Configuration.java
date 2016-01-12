package br.com.ibarra.moclimao.api.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import br.com.ibarra.moclimao.R;

/**
 * Created by joaoibarra on 11/01/16.
 */
public class Configuration {
    private Context context;
    private SharedPreferences sharedPref;
    private String defaultCity;
    private String city;
    private int unit;
    public Configuration(Context context){
        this.context = context;
        this.defaultCity =  context.getResources().getString(R.string.default_city);
        sharedPref = context.getSharedPreferences("CONFIG", Context.MODE_PRIVATE);
        city = sharedPref.getString(context.getString(R.string.city_key), defaultCity);
        unit = sharedPref.getInt(context.getString(R.string.unit_key), R.id.metric_unit);
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(this.context.getString(R.string.city_key), city);
        editor.commit();
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(this.context.getString(R.string.unit_key), unit);
        editor.commit();
    }

    public String getUnitAbbreviation(){
        switch (getUnit()){
            case R.id.metric_unit:
                return context.getResources().getString(R.string.metric_unit);
            case R.id.default_unit:
                return context.getResources().getString(R.string.default_unit);
            case R.id.imperial_unit:
                return context.getResources().getString(R.string.imperial_unit);
            default:
                return context.getResources().getString(R.string.metric_unit);
        }
    }

    public String getUnitToString(){
        switch (getUnit()){
            case R.id.metric_unit:
                return context.getResources().getString(R.string.metric_unit_tostring);
            case R.id.default_unit:
                return context.getResources().getString(R.string.default_unit_tostring);
            case R.id.imperial_unit:
                return context.getResources().getString(R.string.imperial_unit_tostring);
            default:
                return context.getResources().getString(R.string.metric_unit_tostring);
        }
    }
}
