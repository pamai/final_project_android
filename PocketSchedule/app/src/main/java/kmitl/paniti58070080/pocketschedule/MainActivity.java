package kmitl.paniti58070080.pocketschedule;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfos;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference dataRef;
    FirebaseUser user;
    private ScheduleInfo scheduleInfo;
    private List<ScheduleInfo> scheduleInfos;

//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.noDataText)
//    TextView noData;

    @BindView(R.id.pager)
    ViewPager pager;
//
//    private InfoAdapter recycleAdapter;
//    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        scheduleInfo = new ScheduleInfo();
        scheduleInfos = new ArrayList<>();

        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recycleAdapter = new InfoAdapter();
//        recyclerView.setAdapter(recycleAdapter);

        user = FirebaseAuth.getInstance().getCurrentUser();
        dataRef = FirebaseDatabase.getInstance().getReference();
        dataRef = dataRef.child(user.getUid()).child("schedule");
//        dataRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.e("data", Long.toString(dataSnapshot.getChildrenCount()));
//                for (DataSnapshot data : dataSnapshot.getChildren()){
//                    scheduleInfo = data.getValue(ScheduleInfo.class);
//                    scheduleInfos.add(scheduleInfo);
//                    Collections.sort(scheduleInfos, new Comparator<ScheduleInfo>() {
//                        @Override
//                        public int compare(ScheduleInfo scheduleInfo, ScheduleInfo t1) {
//                            return scheduleInfo.getTime_start().compareTo(t1.getTime_start());
//                        }
//                    });
//                    Log.e("loop test", "looped");
//                    if (scheduleInfos != null){
//                        Log.e("not null test", "must display");
//                        displayList(scheduleInfos);
//                    }
//                    else {
//                        Log.e("null test", "NULL!");
//                        displayList(new ArrayList<ScheduleInfo>());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }


    @OnClick(R.id.floatingActionButton)
    public void addButtonClicked(View view){
        Intent intent = new Intent(this, AddInfoActivity.class);
        startActivity(intent);
        finish();
    }

//    public void displayList(List<ScheduleInfo> infoList) {
//        if (infoList.size() <= 0) {
//            noData.setVisibility(View.VISIBLE);
//            recyclerView.setVisibility(View.GONE);
//        } else {
//            noData.setVisibility(View.GONE);
//            recyclerView.setVisibility(View.VISIBLE);
//            recycleAdapter.setData(scheduleInfos);
//            recycleAdapter.notifyDataSetChanged();
//        }
//
//    }

}
