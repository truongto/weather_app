package truongtx.nws.weatherapp.presentation.ui.screen.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import truongtx.nws.weatherapp.R;
import truongtx.nws.weatherapp.api.weather.modelWeatherList.ListAPI;
import truongtx.nws.weatherapp.application.roomDataBase.AppDatabase;
import truongtx.nws.weatherapp.application.roomDataBase.model.History;
import truongtx.nws.weatherapp.application.roomDataBase.thread.HistoryQueryTask;
import truongtx.nws.weatherapp.presentation.presenters.HistoryPresenter;
import truongtx.nws.weatherapp.presentation.ui.adapter.HistoryAdapter;
import truongtx.nws.weatherapp.presentation.ui.screen.history.mvp.HistoryPresenterImpl;

public class HistoryActivity extends AppCompatActivity implements HistoryPresenter, View.OnClickListener {
    private HistoryPresenterImpl historyPresenter;
    private List<History> historyModelList = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    private RecyclerView recyclerView;
    private ImageView imageback;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.recyclerViewHistory);
        imageback = findViewById(R.id.back_history);
        imageback.setOnClickListener(this);

        historyPresenter = new HistoryPresenterImpl(this, this);
        historyAdapter = new HistoryAdapter(this, historyModelList);
        LinearLayoutManager LayoutManagaer = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManagaer);
        recyclerView.setAdapter(historyAdapter);

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "HistoryRooom.db").allowMainThreadQueries().build();
        HistoryQueryTask historyQueryTask = new HistoryQueryTask(this);
        historyQueryTask.getAllHistory(new HistoryQueryTask.OnQuery<List<History>>() {
            @Override
            public void onResult(List<History> histories) {
                HistoryActivity.this.historyModelList.addAll(histories);
                historyAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void dataHisrory(List<ListAPI> listAPIListAPI) {


    }

    @Override
    public void onClick(View view) {
        historyPresenter.backManchinh();
    }
}
