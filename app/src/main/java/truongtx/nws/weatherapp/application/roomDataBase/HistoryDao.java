package truongtx.nws.weatherapp.application.roomDataBase;

import androidx.room.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import truongtx.nws.weatherapp.application.roomDataBase.model.History;
@Dao
public interface HistoryDao {
    @Query("SELECT * FROM history")
    List<History> getAll();
    // thêm 1 hoặc nhiều User
    @Insert
    long[] insertAll(History ... histories);
    // xóa 1 User
    @Delete
    int delete(History[] histories);
}
