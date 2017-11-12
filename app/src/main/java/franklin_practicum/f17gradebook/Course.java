package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Course extends AppCompatActivity {

    private void putArraysInIntent(Intent i){
        i.putExtra("userID", userID);
        i.putExtra("courseID", courseID);
        i.putExtra("assignmentID", assignmentID);
    }

    private void getArraysFromIntent(){
        userID = getIntent().getStringExtra("userID");
        courseID = getIntent().getStringExtra("courseID");
        assignmentID = getIntent().getStringExtra("assignmentID");
    }

    private String userID, courseID, assignmentID;

    private Button assignmentsButton, calendar, attendance, whatIf;
    private TextView courseNameTextView, descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        getArraysFromIntent();

        assignmentsButton = (Button) findViewById(R.id.assignments);
        calendar = (Button) findViewById(R.id.calendar);
        attendance = (Button) findViewById(R.id.attendance);
        whatIf = (Button) findViewById(R.id.whatIf);
        courseNameTextView = (TextView) findViewById(R.id.courseNameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);

        Bundle bundle = getIntent().getExtras();
        String courseName = bundle.getString("courseName");
        userID = getIntent().getStringExtra("userID");
        courseID = getIntent().getStringExtra("courseID");
        //String description= bundle.getString("description");

        courseNameTextView.setText(courseName);
        //descriptionTextView.setText(description);

        assignmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course.this, Assignments.class);
                putArraysInIntent(intent);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course.this, Calendar.class);
                //putArraysInIntent(intent);
                intent.putExtra("userID", userID);
                intent.putExtra("courseID", courseID);
                startActivity(intent);
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course.this, Attendance.class);
                putArraysInIntent(intent);
                startActivity(intent);
            }
        });

        whatIf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course.this, WhatIf.class);
                putArraysInIntent(intent);
                startActivity(intent);
            }
        });

    }
}
