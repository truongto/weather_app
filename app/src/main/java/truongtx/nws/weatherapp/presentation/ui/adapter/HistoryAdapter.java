package truongtx.nws.weatherapp.presentation.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.squareup.picasso.Picasso;

import java.util.List;

import truongtx.nws.weatherapp.R;
import truongtx.nws.weatherapp.application.roomDataBase.AppDatabase;
import truongtx.nws.weatherapp.application.roomDataBase.model.History;
import truongtx.nws.weatherapp.presentation.presenters.onClickRecy.ItemOnClick;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.holder> {
    private Context mContext;
    private List<History> historyModelList;


    private static final String IS_DEGREE = "IS_DEGREE";
    private static final String IS_KELVIN = "IS_KELVIN";
    private AppDatabase appDatabase;

    public HistoryAdapter(Context context, List<History> historyModelList) {
        this.mContext = context;
        this.historyModelList = historyModelList;
    }

    @NonNull
    @Override
    public HistoryAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.history_icon, parent, false);
        return new HistoryAdapter.holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryAdapter.holder holder, final int position) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("key", mContext.MODE_PRIVATE);
        boolean c = sharedPreferences.getBoolean(IS_DEGREE, true);
        boolean k = sharedPreferences.getBoolean(IS_KELVIN, false);

        final History historyModel = historyModelList.get(position);
        holder.chitiet.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        holder.xoa.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        holder.tvgio.setText(historyModel.getGiohientai());
        holder.tvngay.setText(historyModel.getNgayhientai());
        String thanhpho = historyModel.getThanhpho();
        holder.tvthanhpho.setText(thanhpho);
        holder.tvdogio.setText(historyModel.getDogioDeg());
        holder.tvtocdogio.setText(historyModel.getTocdogioSpeed());
        holder.tvdoam.setText(historyModel.getDoamHumidity() + "%");
        holder.tvtrangthai.setText(historyModel.getTrangthaiDescription());
        String icon = historyModel.getIconSql();
        Picasso.with(mContext).load("http://api.openweathermap.org/img/w/" + icon + ".png").into(holder.iconviewHistory);

        double nhietdoC = historyModel.getNhietDoTemp();
        double nhietdoF = historyModel.onConverF(nhietdoC);
        if (c && !k) {
            holder.tvnhietdo.setText(String.valueOf(nhietdoC).substring(0, 2));
        } else if (!c && k) {
            holder.tvnhietdo.setText(String.valueOf(nhietdoF).substring(0, 3));
        }

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDatabase = Room.databaseBuilder(mContext, AppDatabase.class, "HistoryRooom.db").allowMainThreadQueries().build();

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View view1 = LayoutInflater.from(mContext).inflate(R.layout.delete_dialog, null);
                builder.setView(view1);
                final AlertDialog dialog = builder.show();
                Button buttonXoa, buttonThoat;
                buttonXoa = dialog.findViewById(R.id.xoa_diag);
                buttonThoat = dialog.findViewById(R.id.thoat_diag);
                buttonXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        appDatabase.historyDao().delete(historyModel);

                        historyModelList.get(position);
                        historyModelList.remove(historyModel);
                        notifyDataSetChanged();
                        Toast.makeText(mContext, R.string.Toastxoa, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                buttonThoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });
        holder.chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View view1 = LayoutInflater.from(mContext).inflate(R.layout.detail_dialog, null);
                builder.setView(view1);
                final AlertDialog dialog = builder.show();
                TextView gio, ngay, dogio, tocdogio, nhietdo, trangthai, doam, thanhpho;
                ImageView imageView;
                gio = dialog.findViewById(R.id.gio_chitiet);
                ngay = dialog.findViewById(R.id.ngay_chitiet);
                nhietdo = dialog.findViewById(R.id.nhietdo_chitiet);
                thanhpho = dialog.findViewById(R.id.thanhpho_chitiet);
                dogio = dialog.findViewById(R.id.dogio_chitiet);
                tocdogio = dialog.findViewById(R.id.tocdogio_chitiet);
                doam = dialog.findViewById(R.id.doam_chitiet);
                trangthai = dialog.findViewById(R.id.trangthai_chitiet);
                gio.setText(historyModel.getGiohientai());
                ngay.setText(historyModel.getNgayhientai());

                nhietdo.setText(String.valueOf(historyModel.getNhietDoTemp()).substring(0, 2) + "º");
                thanhpho.setText(historyModel.getThanhpho());
                dogio.setText(historyModel.getDogioDeg());
                tocdogio.setText(historyModel.getTocdogioSpeed() + "m/h");
                doam.setText(historyModel.getDoamHumidity() + "%");
                trangthai.setText(historyModel.getTrangthaiDescription());

            }
        });


    }

    @Override
    public int getItemCount() {
        return (historyModelList != null) ? historyModelList.size() : 0;
    }

    public class holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tvngay, tvgio, tvdogio, tvtocdogio, tvnhietdo, tvtrangthai, tvdoam, chitiet, xoa, tvthanhpho;
        ImageView iconviewHistory;
        private ItemOnClick itemOnClick;

        public holder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            tvgio = itemView.findViewById(R.id.gio_history);
            tvngay = itemView.findViewById(R.id.ngay_history);
            tvthanhpho = itemView.findViewById(R.id.thanhpho_history);
            tvdogio = itemView.findViewById(R.id.dogioText_history);
            tvtocdogio = itemView.findViewById(R.id.tocdogioText_history);
            tvnhietdo = itemView.findViewById(R.id.nhietdo_hitory);
            tvtrangthai = itemView.findViewById(R.id.trangthai_history);
            tvdoam = itemView.findViewById(R.id.doamText_history);
            iconviewHistory = itemView.findViewById(R.id.icon_history);
            chitiet = itemView.findViewById(R.id.chitiet_history);
            xoa = itemView.findViewById(R.id.xoa_history);
        }

        public void setItem(ItemOnClick item) {
            this.itemOnClick = item;
        }

        @Override
        public void onClick(View view) {
            itemOnClick.onClick(view, getAdapterPosition(), false);

        }

        @Override
        public boolean onLongClick(View view) {
            itemOnClick.onClick(view, getAdapterPosition(), true);
            return true;
        }
    }
}
