package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Course extends AppCompatActivity {

    private Button assignments, calendar, attendance, whatIf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        assignments = (Button) findViewById(R.id.assignments);
        calendar = (Button) findViewById(R.id.calendar);
        attendance = (Button) findViewById(R.id.attendance);
        whatIf = (Button) findViewById(R.id.whatIf);

        assignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(validEmail && validPassword)
                Intent intent = new Intent(Course.this, Assignments.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(validEmail && validPassword)
                Intent intent = new Intent(Course.this, Calendar.class);
                startActivity(intent);
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(validEmail && validPassword)
                Intent intent = new Intent(Course.this, Attendance.class);
                startActivity(intent);
            }
        });

        whatIf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(validEmail && validPassword)
                Intent intent = new Intent(Course.this, WhatIf.class);
                startActivity(intent);
            }
        });

    }
}
