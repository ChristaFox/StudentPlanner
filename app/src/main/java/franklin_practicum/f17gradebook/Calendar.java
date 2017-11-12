package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

public class Calendar extends AppCompatActivity {

    private static final String TAG = "Calendar";

    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "-" + (month+1) + "-" + dayOfMonth; //formatting the date that is fit in database
                //Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy:" + date);
                //Todo: sending the date through Intent

                Intent intent = new Intent(Calendar.this, AssignmentActivity.class);
                intent.putExtra("userID", getIntent().getStringExtra("userID"));
                intent.putExtra("courseID", getIntent().getStringExtra("courseID"));
                startActivity(intent);

            }

        });
    }
}
