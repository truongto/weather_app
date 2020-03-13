package truongtx.nws.weatherapp.presentation.ui.screen.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.List;
import truongtx.nws.weatherapp.R;
import truongtx.nws.weatherapp.api.weather.modelWeatherList.ListAPI;
import truongtx.nws.weatherapp.presentation.presenters.HistoryPresenter;
import truongtx.nws.weatherapp.presentation.ui.adapter.HistoryAdapter;
import truongtx.nws.weatherapp.presentation.ui.screen.history.mvp.HistoryModel;
import truongtx.nws.weatherapp.presentation.ui.screen.history.mvp.HistoryPresenterImpl;

public class HistoryActivity extends AppCompatActivity implements HistoryPresenter, View.OnClickListener {
    private HistoryPresenterImpl historyPresenter;
    private List<HistoryModel> historyModelList;
    private HistoryAdapter historyAdapter;
    private RecyclerView recyclerView;
    private ImageView imageback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.recyclerViewHistory);
        imageback = findViewById(R.id.back_history);
        imageback.setOnClickListener(this);

        historyPresenter = new HistoryPresenterImpl(this, this);
//        historyModelList = sqlDatabase.getAllHistory();
//        historyAdapter = new HistoryAdapter(this, historyModelList);
        LinearLayoutManager LayoutManagaer = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManagaer);
        recyclerView.setAdapter(historyAdapter);
    }

    @Override
    public void dataHisrory(List<ListAPI> listAPIListAPI) {

    }

    @Override
    public void onClick(View view) {
        historyPresenter.backManchinh();
    }
}
