package br.com.ibarra.moclimao.util;

import java.util.Calendar;

/**
 * Created by joaoibarra on 11/01/16.
 */
public class Util {

    public static String dateToString(String dateString){
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis((long) Integer.parseInt(dateString) * 1000);
        return date.get(Calendar.DAY_OF_MONTH) + "/" + String.format("%02d", date.get(Calendar.MONTH) + 1);
    }

    public static String temperatureDoubleToString(String temperature){
        int intTemperature = (int) Double.parseDouble(temperature);
        return Integer.toString(intTemperature);
    }
}
