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

    public class assignment{
        public String assignID, userID, courseID, assignName, assignStartDate, assignEndDate, pointsPossible, pointsEarned, currentGradeGoal;
        public assignment(String assignID, String userID, String courseID, String assignName, String assignStartDate, String assignEndDate,
                          String pointsPossible, String pointsEarned, String currentGradeGoal){
            this.assignID = assignID;
            this.userID = userID;
            this.courseID = courseID;
            this.assignName = assignName;
            this.assignStartDate = assignStartDate;
            this.assignEndDate = assignEndDate;
            this.pointsPossible = pointsPossible;
            this.pointsEarned = pointsEarned;
            this.currentGradeGoal = currentGradeGoal;
        }
    }

    private String userID, courseID, assignmentID;

    ArrayList<Object> assignmentList=new ArrayList<Object>();
    AssignmentsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        getArraysFromIntent();

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
                putArraysInIntent(intent);
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