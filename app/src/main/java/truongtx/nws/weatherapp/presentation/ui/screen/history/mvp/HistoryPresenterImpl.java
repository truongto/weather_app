package truongtx.nws.weatherapp.presentation.ui.screen.history.mvp;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import truongtx.nws.weatherapp.api.weather.modelWeatherList.ListAPI;
import truongtx.nws.weatherapp.domains.interactors.WeatherInteractor;
import truongtx.nws.weatherapp.presentation.presenters.HistoryPresenter;
import truongtx.nws.weatherapp.presentation.ui.screen.main.MainActivity;

public class HistoryPresenterImpl {
    private WeatherInteractor weatherInteractor;
    private HistoryPresenter mainPresenter;
    private Context mcontext;
    private List<ListAPI> listAPIS = new ArrayList<>();

    public HistoryPresenterImpl(HistoryPresenter mainPresenter, Context context) {
        this.mainPresenter = mainPresenter;
        this.mcontext = context;
    }

    public void backManchinh() {
        Intent intent = new Intent(mcontext, MainActivity.class);
        mcontext.startActivity(intent);
    }
}
