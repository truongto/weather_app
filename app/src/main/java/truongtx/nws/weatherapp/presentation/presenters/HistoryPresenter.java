package truongtx.nws.weatherapp.presentation.presenters;

import java.util.List;

import truongtx.nws.weatherapp.api.weather.modelWeatherList.ListAPI;

public interface HistoryPresenter {
    void dataHisrory(List<ListAPI> listAPIListAPI);
}
