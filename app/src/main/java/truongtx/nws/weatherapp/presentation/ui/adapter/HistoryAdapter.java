package truongtx.nws.weatherapp.presentation.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import truongtx.nws.weatherapp.R;
import truongtx.nws.weatherapp.presentation.presenters.onClickRecy.ItemOnClick;
import truongtx.nws.weatherapp.presentation.ui.screen.history.mvp.HistoryModel;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.holder> {
    private Context mContext;
    private List<HistoryModel> historyModelList;
    private String pho;
    private static final String IS_DEGREE = "IS_DEGREE";
    private static final String IS_KELVIN = "IS_KELVIN";

    @NonNull
    @Override
    public HistoryAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.history_icon, parent, false);
        return new HistoryAdapter.holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.holder holder, final int position) {

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
