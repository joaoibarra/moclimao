package br.com.ibarra.moclimao.api.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.ibarra.moclimao.api.service.interfaces.OpenWeatherMapService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joaoibarra on 05/01/16.
 */
public class OpenWeatherMapServiceImpl {

    public static OpenWeatherMapService openWeatherMapService;

    public static OpenWeatherMapService getInstance() {
        if (openWeatherMapService == null)
            return new OpenWeatherMapServiceImpl().create();

        return openWeatherMapService;
    }

    private OpenWeatherMapServiceImpl() {
    }

    public OpenWeatherMapService create() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        openWeatherMapService = retrofit.create(OpenWeatherMapService.class);
        return openWeatherMapService;
    }
}
