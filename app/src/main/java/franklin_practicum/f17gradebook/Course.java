package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Course extends AppCompatActivity {

    private ArrayList<String> courses, assignments, courseGrades, assignmentGrades, assignmentDueDates;

    private void putArraysInIntent(Intent i){
        i.putExtra("courses", courses);
        i.putExtra("assignments", assignments);
        i.putExtra("courseGrades", courseGrades);
        i.putExtra("assignmentGrades", assignmentGrades);
        i.putExtra("assignmentDueDates", assignmentDueDates);
    }

    public ArrayList<String> getAssignments(){
        return assignments;
    }

    public void setAssignments(ArrayList<String> newList){
        assignments = newList;
    }

    private void getArraysFromIntent(){
        String[] coursesS = getIntent().getStringArrayExtra("courses");
        String[] assignmentsS = getIntent().getStringArrayExtra("assignments");
        String[] courseGradesS = getIntent().getStringArrayExtra("courseGrades");
        String[] assignmentGradesS = getIntent().getStringArrayExtra("assignmentGrades");
        String[] assignmentDueDatesS = getIntent().getStringArrayExtra("assignmentDueDates");
        for(String s: coursesS){courses.add(s);}
        for(String s: assignmentsS){assignments.add(s);}
        for(String s: courseGradesS){courseGrades.add(s);}
        for(String s: assignmentGradesS){assignmentGrades.add(s);}
        for(String s: assignmentDueDatesS){assignmentDueDates.add(s);}
    }

    private Button assignmentsButton, calendar, attendance, whatIf;
    private TextView courseNameTextView, descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        //getArraysFromIntent();

        assignmentsButton = (Button) findViewById(R.id.assignments);
        calendar = (Button) findViewById(R.id.calendar);
        attendance = (Button) findViewById(R.id.attendance);
        whatIf = (Button) findViewById(R.id.whatIf);
        courseNameTextView = (TextView) findViewById(R.id.courseNameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);

        Bundle bundle = getIntent().getExtras();
        String courseName= bundle.getString("courseName");
        String description= bundle.getString("description");

        courseNameTextView.setText(courseName);
        descriptionTextView.setText(description);

        assignmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course.this, Assignments.class);
                //putArraysInIntent(intent);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course.this, Calendar.class);
                //putArraysInIntent(intent);
                startActivity(intent);
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course.this, Attendance.class);
                //putArraysInIntent(intent);
                startActivity(intent);
            }
        });

        whatIf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course.this, WhatIf.class);
                //putArraysInIntent(intent);
                startActivity(intent);
            }
        });

    }
}
