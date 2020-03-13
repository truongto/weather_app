package truongtx.nws.weatherapp.api;


import truongtx.nws.weatherapp.api.weather.modelWeatherAPI.Weather;
import truongtx.nws.weatherapp.api.weather.modelWeatherList.WeatherList;
import truongtx.nws.weatherapp.utils.Enums;

public interface APICallListener {
    void onAPICallSucceed(Enums.APIRoute route, Weather weather);

    void onAPICallSucceedList(WeatherList weatherList);

    void onAPICallSucceedCity(WeatherList weatherCity);

    void onAPICallFailed(Enums.APIRoute route, Throwable throwable);
}
