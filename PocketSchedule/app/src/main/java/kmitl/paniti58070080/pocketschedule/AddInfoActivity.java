package kmitl.paniti58070080.pocketschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import kmitl.paniti58070080.pocketschedule.exception.EmptyLocationException;
import kmitl.paniti58070080.pocketschedule.exception.EmptySubjectException;
import kmitl.paniti58070080.pocketschedule.exception.InvalidClassTimeException;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;
import kmitl.paniti58070080.pocketschedule.validation.EmptyLocationValidation;
import kmitl.paniti58070080.pocketschedule.validation.EmptySubjectValidation;
import kmitl.paniti58070080.pocketschedule.validation.ScheduleValidation;
import kmitl.paniti58070080.pocketschedule.validation.TimeLeapValidation;

public class AddInfoActivity extends AppCompatActivity {

    private SwipeSelector swipeSelector;
    @BindView(R.id.addSubject)
    TextView subject;
    @BindView(R.id.addLocation)
    TextView location;
    @BindView(R.id.startPicker)
    TimePicker class_start;
    @BindView(R.id.endPicker)
    TimePicker class_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        ButterKnife.bind(this);

        class_start.setIs24HourView(true);
        class_end.setIs24HourView(true);

        swipeSelector = findViewById(R.id.swipeSelector);
        swipeSelector.setItems(
                new SwipeItem(0, "Monday", "Monday?! But, I wasn’t even finished with Saturday yet.."),
                new SwipeItem(1, "Tuesday", "Tuesday isn't so bad...It's a sign that I've somehow survived Monday."),
                new SwipeItem(2, "Wednesday", "Wednesdays are like Mondays in the middle of the week!"),
                new SwipeItem(3, "Thursday", "Nothing screws up your Friday like realizing its Thursday."),
                new SwipeItem(4, "Friday", "Friday! Finally, I made it."),
                new SwipeItem(5, "Saturday", "Hello weekend!"),
                new SwipeItem(6, "Sunday", "Monday is coming!!")
        );

    }

    @OnClick(R.id.addInfoButton)
    public void addInfo(View view){
        final DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("Tag", swipeSelector.getSelectedItem().title.toString());
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String timeStart = timeRefactor(Integer.toString(class_start.getCurrentHour())) + "." + timeRefactor(Integer.toString(class_start.getCurrentMinute()));
                String timeEnd = timeRefactor(Integer.toString(class_end.getCurrentHour())) + "." + timeRefactor(Integer.toString(class_end.getCurrentMinute()));
                String key = dataRef.push().getKey().toString();
                ScheduleInfo scheduleInfo = new ScheduleInfo(swipeSelector.getSelectedItem().title.toString(),timeStart, timeEnd, location.getText().toString(), subject.getText().toString(), key);
                if (dataValidation(scheduleInfo)){
                    dataRef.child(user.getUid()).child("schedule").child(key).setValue(scheduleInfo);
                    Intent intent = new Intent(AddInfoActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Intent intent = new Intent(AddInfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @OnClick(R.id.cancelButton)
    public void cancelInfo(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean dataValidation(ScheduleInfo scheduleInfo){
        List<ScheduleValidation> validations = new ArrayList<>();
        validations.add(new EmptySubjectValidation());
        validations.add(new EmptyLocationValidation());
        validations.add(new TimeLeapValidation());
        try{
            for (ScheduleValidation validation : validations){
                validation.validate(scheduleInfo);
            }
        } catch (EmptyLocationException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        } catch (EmptySubjectException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        } catch (InvalidClassTimeException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public String timeRefactor(String time){
        if (time.length() == 1){
            return "0"+time;
        }
        return time;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddInfoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
