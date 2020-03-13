package truongtx.nws.weatherapp.application.roomDataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import truongtx.nws.weatherapp.application.roomDataBase.model.History;

@Database(entities = {History.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HistoryDao historyDao();
}
