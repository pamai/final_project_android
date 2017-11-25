package kmitl.paniti58070080.pocketschedule;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.SearchResultHolder>{

    private List<ScheduleInfo> data;

    public InfoAdapter() {
        this.data = new ArrayList<>();
    }

    public void setData(List<ScheduleInfo> data){
        this.data = data;
    }

    @Override
    public SearchResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_info, null, false);
        return new SearchResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchResultHolder holder, int position) {
        ScheduleInfo info = data.get(position);
        holder.textSubject.setText(info.getSubject());
        holder.textLocation.setText(info.getLocation());
        String time = info.getTime_start() + " - " + info.getTime_end();
        holder.textTime.setText(time);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class SearchResultHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textSubject)
        public TextView textSubject;

        @BindView(R.id.textLocation)
        public TextView textLocation;

        @BindView(R.id.textTime)
        public TextView textTime;

        public SearchResultHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
