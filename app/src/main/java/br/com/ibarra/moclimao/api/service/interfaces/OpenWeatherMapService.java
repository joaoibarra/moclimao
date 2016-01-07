package br.com.ibarra.moclimao.api.service.interfaces;


import br.com.ibarra.moclimao.api.models.WeatherToday;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by joaoibarra on 05/01/16.
 */
public interface OpenWeatherMapService {
    String APPID = "f80e716710bb0e8dfead4c2017034aec";
    @Headers({"Accept: Application/JSON"})
    @GET("/data/2.5/weather?APPID=" + APPID)
    Call<WeatherToday> getWeatherToday(@Query("q") String city, @Query("units") String units);
}
