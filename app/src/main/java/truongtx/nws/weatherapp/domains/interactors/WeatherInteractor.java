package truongtx.nws.weatherapp.domains.interactors;

import android.util.Log;
import truongtx.nws.weatherapp.api.APICallListener;
import truongtx.nws.weatherapp.api.APICallManager;
import truongtx.nws.weatherapp.api.APICallManagerCity;
import truongtx.nws.weatherapp.api.APICallManagerList;
import truongtx.nws.weatherapp.api.weather.modelWeatherAPI.Weather;
import truongtx.nws.weatherapp.api.weather.modelWeatherList.WeatherList;
import truongtx.nws.weatherapp.application.GPSTracker;
import truongtx.nws.weatherapp.utils.Enums;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherInteractor {
    APICallListener listener;
    public WeatherInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetContacts(GPSTracker gpsTracker) {
        final Enums.APIRoute route = Enums.APIRoute.GET_WEATHER;
        Call<Weather> call = APICallManager.getInstance().peopleManager.getContacts(gpsTracker);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                listener.onAPICallSucceed(route, response.body());
            }
            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                listener.onAPICallFailed(route, t);
                Log.e("Ket Quaaaaaaaa", String.valueOf(t));
            }
        });

    }

    public void callAPIlist(GPSTracker gpsTracker) {

        Call<WeatherList> call = APICallManagerList.getListDay().peopleManagerList.getContactsListDay(gpsTracker);
        call.enqueue(new Callback<WeatherList>() {
            @Override
            public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                listener.onAPICallSucceedList(response.body());
                Log.e("Chay vao Dung", String.valueOf(response.body()));
            }
            @Override
            public void onFailure(Call<WeatherList> call, Throwable t) {
                Log.e("Ket Qua", String.valueOf(t));
            }
        });

    }

    public void callAPICity( String s) {
        Call<WeatherList> call = APICallManagerCity.getCity().peopleManagerCity.getContactsCityDay(s);
        call.enqueue(new Callback<WeatherList>() {
            @Override
            public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                listener.onAPICallSucceedCity(response.body());
                Log.e("Chay vao ", String.valueOf(response.body()));
            }
            @Override
            public void onFailure(Call<WeatherList> call, Throwable t) {
                Log.e("Ket Qua", String.valueOf(t));
            }
        });

    }
}
