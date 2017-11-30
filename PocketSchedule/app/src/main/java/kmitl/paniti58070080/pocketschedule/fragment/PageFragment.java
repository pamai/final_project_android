package kmitl.paniti58070080.pocketschedule.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import kmitl.paniti58070080.pocketschedule.InfoAdapter;
import kmitl.paniti58070080.pocketschedule.R;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;

public class PageFragment extends Fragment {

    RecyclerView recyclerView;

    TextView noData;

    String day;

    private InfoAdapter recycleAdapter;

    private DatabaseReference dataRef;
    FirebaseUser user;
    private ScheduleInfo scheduleInfo;
    private List<ScheduleInfo> scheduleInfos;

    public PageFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public PageFragment(String day) {
        this.day = day;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recycleAdapter = new InfoAdapter();
        recyclerView.setAdapter(recycleAdapter);
        noData = rootView.findViewById(R.id.noDataText);
        scheduleInfo = new ScheduleInfo();
        scheduleInfos = new ArrayList<>();

        user = FirebaseAuth.getInstance().getCurrentUser();
        Log.e("user", user.toString());
        Log.e("user", user.getUid());
        dataRef = FirebaseDatabase.getInstance().getReference();
        dataRef = dataRef.child(user.getUid()).child("schedule");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("data", Long.toString(dataSnapshot.getChildrenCount()));
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    scheduleInfo = data.getValue(ScheduleInfo.class);
                    if (scheduleInfo.getDay().equals(day)) {
                        scheduleInfos.add(scheduleInfo);
                    }
                    Collections.sort(scheduleInfos, new Comparator<ScheduleInfo>() {
                        @Override
                        public int compare(ScheduleInfo scheduleInfo, ScheduleInfo t1) {
                            return scheduleInfo.getTime_start().compareTo(t1.getTime_start());
                        }
                    });
                    if (scheduleInfos != null){
                        displayList(scheduleInfos);
                    }
                    else {
                        displayList(new ArrayList<ScheduleInfo>());
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.e("test", "OK let's go");

        return rootView;
    }

        public void displayList(List<ScheduleInfo> infoList) {
        if (infoList.size() <= 0) {
            noData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recycleAdapter.setData(scheduleInfos);
            recycleAdapter.notifyDataSetChanged();
            Log.e("data set", "notifyed");
        }

    }

}
