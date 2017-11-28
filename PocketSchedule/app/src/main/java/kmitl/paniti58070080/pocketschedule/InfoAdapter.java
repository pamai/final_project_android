package kmitl.paniti58070080.pocketschedule;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ResultHolder>{

    private List<ScheduleInfo> data;

    public InfoAdapter() {
        this.data = new ArrayList<>();
    }

    public void setData(List<ScheduleInfo> data){
        this.data = data;
    }

    @Override
    public ResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_info, null, false);
        return new ResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResultHolder holder, int position) {
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

    public class ResultHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.textSubject)
        public TextView textSubject;

        @BindView(R.id.textLocation)
        public TextView textLocation;

        @BindView(R.id.textTime)
        public TextView textTime;

        public ResultHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(final View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add("Edit");
            arrayAdapter.add("Delete");
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            });
            builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 0){
                        Log.e("OnDialog", "i = 0");
                        Intent intent = new Intent(view.getContext().getApplicationContext(), AddInfoActivity.class);
                        Log.e("key", data.get(getAdapterPosition()).getKey());
                        intent.putExtra("key", data.get(getAdapterPosition()).getKey());
                        intent.putExtra("schedule", data.get(getAdapterPosition()).getSubject());
                        intent.putExtra("location", data.get(getAdapterPosition()).getLocation());
                        ((Activity) view.getContext()).finish();
                        view.getContext().startActivity(intent);
                    }
                    else {
                        Log.e("OnDialog", "i = 1");
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();
                        dataRef = dataRef.child(user.getUid()).child("schedule").child(data.get(getAdapterPosition()).getKey());
                        dataRef.setValue(null);
                        ((Activity) view.getContext()).finish();
                        (view.getContext()).startActivity(((Activity) view.getContext()).getIntent());
                    }
                }
            });
            builder.show();
            Log.e("OnClick", Integer.toString(getAdapterPosition()));
            Log.e("OnClick", data.get(getAdapterPosition()).getSubject());
            Log.e("OnClick", data.get(getAdapterPosition()).getKey());
        }
    }

}
