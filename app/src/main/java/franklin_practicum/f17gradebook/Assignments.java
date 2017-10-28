package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Assignments extends AppCompatActivity {

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

    ArrayList<Object> assignmentList=new ArrayList<Object>();
    AssignmentsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        //getArraysFromIntent();

        ListView list = (ListView) findViewById(R.id.listView);
        adapter=new AssignmentsListAdapter(list.getContext(), assignmentList);

        list.setAdapter(adapter);

        /*
        for (String s: assignments) {
            adapter.add(s);
        }
        */

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Assignments.this, WhatIf.class);
                //putArraysInIntent(intent);
                startActivity(intent);
            }
        });

        ImageView addButton = (ImageView) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("Test");
            }
        });
    }
}