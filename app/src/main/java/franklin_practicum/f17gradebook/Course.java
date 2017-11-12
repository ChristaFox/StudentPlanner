package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        Toast.makeText(getApplicationContext(), "userID: "+userID, Toast.LENGTH_LONG).show();

        assignmentsButton = (Button) findViewById(R.id.assignments);
        calendar = (Button) findViewById(R.id.calendar);
        attendance = (Button) findViewById(R.id.attendance);
        whatIf = (Button) findViewById(R.id.whatIf);
        courseNameTextView = (TextView) findViewById(R.id.courseNameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        TextView currentAttendance = (TextView) findViewById(R.id.textView11);
        TextView currentGrade = (TextView) findViewById(R.id.textView12);
        TextView gradeGoal = (TextView) findViewById(R.id.textView13);

        Bundle bundle = getIntent().getExtras();
        String courseName = bundle.getString("courseName");
        userID = getIntent().getStringExtra("userID");
        courseID = getIntent().getStringExtra("courseID");
        String description = getIntent().getStringExtra("description");

        /*
        intent.putExtra("absences", course.absences);
        intent.putExtra("absencesAllowed", course.absencesAllowed);
        intent.putExtra("currentGrade", course.currentGrade);
        intent.putExtra("currentGradeGoal", course.currentGradeGoal);
        */
        currentAttendance.setText("Current Attendance: "+getIntent().getStringExtra("absences")+" of "+
        getIntent().getStringExtra("absencesAllowed")+" absences allowed");
        currentGrade.setText("Current Grade: "+getIntent().getStringExtra("currentGrade"));
        gradeGoal.setText("Grade Goal: "+getIntent().getStringExtra("currentGradeGoal"));

        courseNameTextView.setText(courseName);
        descriptionTextView.setText(description);

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
