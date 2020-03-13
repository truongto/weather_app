package truongtx.nws.weatherapp.presentation.ui.screen.searchCity.mvp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.room.Room;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import truongtx.nws.weatherapp.R;
import truongtx.nws.weatherapp.api.APICallListener;
import truongtx.nws.weatherapp.api.weather.modelWeatherAPI.Weather;
import truongtx.nws.weatherapp.api.weather.modelWeatherList.ListAPI;
import truongtx.nws.weatherapp.api.weather.modelWeatherList.WeatherList;
import truongtx.nws.weatherapp.application.roomDataBase.AppDatabase;
import truongtx.nws.weatherapp.application.roomDataBase.model.History;
import truongtx.nws.weatherapp.application.roomDataBase.thread.HistoryQueryTask;
import truongtx.nws.weatherapp.domains.interactors.WeatherInteractor;
import truongtx.nws.weatherapp.presentation.presenters.AboutPresenter;
import truongtx.nws.weatherapp.presentation.ui.screen.main.MainActivity;
import truongtx.nws.weatherapp.utils.Enums;

public class AboutPresenterImpl implements APICallListener {
    WeatherInteractor weatherInteractor;
    AboutPresenter mView;
    Context mcontext;
    List<ListAPI> listCityList = new ArrayList<>();
    private String gio, ngay, dogio, tocdogio, doam, trangthai, icon;
    private double nhietdo, nhietdoF;
    private double s;
    private double s1;
    // RoomDatacbase
    private AppDatabase appDatabase;
    private History historyModel = new History();
    private HistoryQueryTask historyQueryTask;

    private static final String IS_DEGREE = "IS_DEGREE";
    private static final String IS_KELVIN = "IS_KELVIN";

    public AboutPresenterImpl(AboutPresenter mView, Context context) {
        this.mView = mView;
        this.mcontext = context;
        this.weatherInteractor = new WeatherInteractor(this);
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, Weather weather) {
    }

    @Override
    public void onAPICallSucceedList(WeatherList weatherList) {

    }

    @Override
    public void onAPICallSucceedCity(WeatherList weatherCity) {
        listCityList = weatherCity.getList();
        mView.getRecyCity(listCityList);
        String thanhpho = weatherCity.getCity().getName();
        String nhietdo = String.valueOf(weatherCity.getList().get(0).getMain().getTemp()).substring(0, 2);
        String iconchinh = weatherCity.getList().get(0).getWeather().get(0).getIcon();
        s = listCityList.get(0).getMain().getTemp();
        s1 = listCityList.get(0).getMain().onConvertCelsiusToF(s);

        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("key", mcontext.MODE_PRIVATE);
        boolean c = sharedPreferences.getBoolean(IS_DEGREE, true);
        boolean k = sharedPreferences.getBoolean(IS_KELVIN, false);
        if (c && !k) {
            mView.nhietdoC(s);

        } else if (!c && k) {
            mView.nhietdoF(s1);

        }
        mView.icon(iconchinh);
        mView.thanhpho(thanhpho);


    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {

    }

    public void Tim(String snhap) {
        weatherInteractor.callAPICity(snhap);
        historyModel.setThanhpho(snhap);
    }

    public void insetLichSu(List<ListAPI> listCityList) {
        appDatabase = Room.databaseBuilder(mcontext, AppDatabase.class, "HistoryRooom.db").allowMainThreadQueries().build();

        nhietdo = listCityList.get(0).getMain().getTemp();
        nhietdoF = listCityList.get(0).getMain().onConvertCelsiusToF(nhietdo);
        dogio = String.valueOf(listCityList.get(0).getWind().getDeg());
        tocdogio = String.valueOf(listCityList.get(0).getWind().getSpeed());
        doam = String.valueOf(listCityList.get(0).getMain().getHumidity());
        trangthai = listCityList.get(0).getWeather().get(0).getDescription();
        icon = listCityList.get(0).getWeather().get(0).getIcon();
        Calendar calendar = Calendar.getInstance();
        gio = DateFormat.getTimeInstance(DateFormat.MEDIUM).format(calendar.getTime());
        ngay = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
        historyModel.setGiohientai(gio);
        historyModel.setNgayhientai(ngay);
        historyModel.setNhietDoTemp(nhietdo);
        historyModel.setDogioDeg(dogio);
        historyModel.setTocdogioSpeed(tocdogio);
        historyModel.setDoamHumidity(doam);
        historyModel.setTrangthaiDescription(trangthai);
        historyModel.setIconSql(icon);
        long[] kiemtra = appDatabase.historyDao().insertAll(historyModel);
        if (kiemtra[0] > 0) {
            Toast.makeText(mcontext, R.string.ThemLichSu, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(mcontext, R.string.ThemLichSuThatBai, Toast.LENGTH_LONG).show();
        }
    }

    public void quaylai() {
        Intent intent = new Intent(mcontext, MainActivity.class);
        mcontext.startActivity(intent);
    }
}
