package truongtx.nws.weatherapp.presentation.presenters;

import java.util.List;

import truongtx.nws.weatherapp.api.weather.modelWeatherList.ListAPI;

public interface AboutPresenter {
    void thanhpho(String thanhpho);

    void getRecyCity(List<ListAPI> listCityList);

    void icon(String icon);

    void nhietdoF(Double F);

    void nhietdoC(Double C);
//    interface AboutView extends BaseView {
//        enum ViewState {
//            IDLE, LOADING, SHOW_ABOUT, ERROR
//        }
//
//        void showState(ViewState state);
//
//        AboutModel doRetrieveModel();
//    }
//
//    void presentState(AboutView.ViewState state);
}
