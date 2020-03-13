package truongtx.nws.weatherapp.api.weather;

import truongtx.nws.weatherapp.api.weather.modelWeatherAPI.Weather;
import truongtx.nws.weatherapp.api.weather.modelWeatherList.WeatherList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherService {
    @GET("v2/nearest_city?key=cd058c32-889e-467e-a840-e640670099d2")
    Call<Weather> getPeople(@Query("lat") double lat,
                            @Query("lon") double lon);

    @GET("data/2.5/forecast?appid=ecdd51cc4c60e1fb08cf11263bbb546a&units=metric&lang=vi")
    Call<WeatherList> getWeather(@Query("lat") double lat,
                                 @Query("lon") double lon);

    @GET("data/2.5/forecast?appid=ecdd51cc4c60e1fb08cf11263bbb546a&units=metric&lang=vi")
    Call<WeatherList> getWeatherCity(@Query("q") String q);
}
