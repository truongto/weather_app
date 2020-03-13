package truongtx.nws.weatherapp.application.roomDataBase.thread;

import android.content.Context;
import android.os.AsyncTask;
import androidx.room.Room;
import java.util.List;
import truongtx.nws.weatherapp.application.roomDataBase.AppDatabase;
import truongtx.nws.weatherapp.application.roomDataBase.model.History;

public class HistoryQueryTask {
    public AppDatabase appDatabase;

    public HistoryQueryTask(Context context) {
        this.appDatabase = Room.databaseBuilder(context, AppDatabase.class, "HistoryRooom.db").build();
    }
    public interface OnQuery<T> {
        void onResult(T t);
    }



    public void getAllHistory(OnQuery<List<History>> onQuery) {
        new GetHistorysAsyncTask(onQuery).execute();
    }

    public void insertHistory(OnQuery<long[]> onQuery, History... histories) {
        new InsertHistoryAsyncTask(onQuery).execute();
    }
    public void deleteHistory(OnQuery<int[]> onQuery, History history) {
        new deleteHistoryAsyncTask(onQuery).execute();
    }


//tao luong getall
    private class GetHistorysAsyncTask extends AsyncTask<Void, Void, List<History>> {
        OnQuery onQuery;

        public GetHistorysAsyncTask(OnQuery onQuery) {
            this.onQuery = onQuery;
        }

        @Override
        protected List<History> doInBackground(Void... voids) {
            return appDatabase.historyDao().getAll();
        }

        @Override
        protected void onPostExecute(List<History> histories) {
            super.onPostExecute(histories);
            onQuery.onResult(histories);
        }


    }


    //tao luong insert
    private class InsertHistoryAsyncTask extends AsyncTask<History, Void, long[]> {
        OnQuery onQuery;

        public InsertHistoryAsyncTask(OnQuery onQuery) {
            this.onQuery = onQuery;
        }

        @Override
        protected long[] doInBackground(History... histories) {
            return appDatabase.historyDao().insertAll(histories);
        }

        @Override
        protected void onPostExecute(long[] longs) {
            super.onPostExecute(longs);
            onQuery.onResult(longs);
        }
    }

    //tao luong delete
    private class deleteHistoryAsyncTask extends AsyncTask<History,Void,Integer> {
        OnQuery onQuery;

        public deleteHistoryAsyncTask(OnQuery onQuery) {
            this.onQuery = onQuery;
        }


        @Override
        protected Integer doInBackground(History...histories) {
            return appDatabase.historyDao().delete(histories);
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
        }
    }
}
