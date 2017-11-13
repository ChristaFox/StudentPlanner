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
import android.widget.Toast;

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

    ArrayList<assignment> assignmentList=new ArrayList<assignment>();
    AssignmentsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        getArraysFromIntent();

        //Toast.makeText(getApplicationContext(), "courseID: "+courseID, Toast.LENGTH_LONG).show();

        ListView list = (ListView) findViewById(R.id.listView);
        adapter=new AssignmentsListAdapter(this, assignmentList);

        list.setAdapter(adapter);

        new FrankAssignData().execute();

        //adapter.add("testName","11/7/2017","30/30");

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
        /*
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("Assignment", "", " / ");
                adapter.notifyDataSetChanged();
            }
        });
        */
    }

    public class FrankAssignData extends AsyncTask {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                DataUtil dataUtil = new DataUtil("CourseAssignmentListSelect.php?CourseID="+courseID+"&UserID="+userID);
                String jsonString = dataUtil.process(null);
                //Log.d(TAG, jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);

                int length = jsonArray.length();
                System.out.println(length);
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    assignmentList.add(new Assignments.assignment(jsonObj.getString("id"),userID,courseID,jsonObj.getString("name"),"",jsonObj.getString("dueDate"),jsonObj.getString("pointsPossible"),jsonObj.getString("pointsEarned"),jsonObj.getString("pointsGoal")));
                    System.out.println(assignmentList.get(i).assignID.toString());
                    System.out.println(assignmentList.get(i).assignName.toString());

                }
                return jsonArray;

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

                        int length = assignmentList.size();
                        for (int i = 0; i < length; i++) {
                            adapter.add(assignmentList.get(i).assignName, assignmentList.get(i).assignEndDate, assignmentList.get(i).pointsEarned + "/" + assignmentList.get(i).pointsPossible);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        }
    }

}