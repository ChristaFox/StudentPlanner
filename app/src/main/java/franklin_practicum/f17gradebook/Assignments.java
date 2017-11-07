package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

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
        public assignment(){}
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

    public ArrayList<assignment> assignments = new ArrayList<assignment>();

    ArrayList<Object> assignmentList=new ArrayList<Object>();
    AssignmentsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        getArraysFromIntent();

        new FrankAssignData().execute();

        ListView list = (ListView) findViewById(R.id.listView);
        adapter=new AssignmentsListAdapter(list.getContext(), assignmentList);

        list.setAdapter(adapter);

        adapter.add("testName","11/7/2017","30/30");
        int length = assignments.size();
        for (int i = 0; i < length; i++) {
            adapter.add(assignments.get(i).assignName, assignments.get(i).assignEndDate, assignments.get(i).pointsEarned+"/"+assignments.get(i).pointsPossible);
        }

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
                adapter.add("Assignment", "", " / ");
            }
        });
    }

    public class FrankAssignData extends AsyncTask {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                DataUtil dataUtil = new DataUtil("CourseAssignmentListSelect.php?CourseID="+courseID+"UserID="+userID);
                //DataUtil dataUtil = new DataUtil("CourseAssignmentListSelect.php?CourseID="+"6"+"UserID="+"30");
                String jsonString = dataUtil.process(null);
                //Log.d(TAG, jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);

                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    assignments.add(new Assignments.assignment());
                    assignments.get(i).assignID = jsonObj.getString("id");
                    assignments.get(i).pointsPossible = jsonObj.getString("pointsPossible");
                    assignments.get(i).pointsEarned = jsonObj.getString("pointsEarned");
                    assignments.get(i).currentGradeGoal = jsonObj.getString("pointsGoal");
                    assignments.get(i).assignEndDate = jsonObj.getString("dueDate");

                }
                return jsonArray;

                /*
			array_push($result, array('name' => $row["AssignmentName"],

						  'dueDate' => $row["AssignmentEndDate"],
						  'pointsPossible' => $row ["AssignmentPointsPossible"],
						  'pointsEarned' => $row ["AssignmentPointsEarned"],
						  'pointsGoal' => $row ["AssignmentCurrentGoal"]));

                */


            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                    //expandableListAdapter = new CustomExpandableListAdapter(CourseActivity.this, expandableListTitle, expandableListDetail);
                    //expandableListView.setAdapter(expandableListAdapter);
                }
            });
        }
    }

}