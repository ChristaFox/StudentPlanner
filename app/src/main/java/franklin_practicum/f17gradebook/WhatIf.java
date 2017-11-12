package franklin_practicum.f17gradebook;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WhatIf extends AppCompatActivity {

    private void putArraysInIntent(Intent i){
        i.putExtra("userID", userID);
        i.putExtra("courseID", courseID);
        i.putExtra("assignmentID", assignmentID);
    }

    private void getArraysFromIntent(){
        //userID = getIntent().getStringExtra("userID");
        //courseID = getIntent().getStringExtra("courseID");
        assignmentID = getIntent().getStringExtra("assignmentID");
        //DELETE BEFORE PUSH
        userID = "30";
        courseID = "6";
    }

    //Variables
    private String userID, courseID, assignmentID;
    private ArrayList<Assignments> assignments;
    private ListView assignmentList;
    private StringBuilder assignString;
    private EditText whatIfPts;
    private Button calculate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_if);
        getArraysFromIntent();

        assignments = new ArrayList<>();
        assignString = new StringBuilder();
        getAssignments();
        assignmentList = (ListView) findViewById(R.id.listView2);
        calculate = (Button) findViewById(R.id.button3);

    }



    //***TO DO*** -> Write code for calculate button

    //whatIfPoints = getText().toString();

    //Adapter
    private class WhatIfAssignAdapter extends ArrayAdapter<Assignments> {
        private WhatIfAssignAdapter() {
            super(WhatIf.this, R.layout.activity_what_if_list, assignments);
        }


    //Create view to show Assignment Name, Points and whatIfPoints

    public View getView(int position, View convertView, ViewGroup parent) {
        View assignView = convertView;
        if (assignView == null) {
            assignView = getLayoutInflater().inflate(R.layout.activity_what_if_list, parent, false);
        }

        int count = 0;
        while (assignments.size() > count) {
            Assignments assign = assignments.get(position);
            count++;

            //Lookup view for data population
            TextView name = (TextView) assignView.findViewById(R.id.editText7);
            TextView ptsAvail = (TextView) assignView.findViewById(R.id.editText8);
            whatIfPts = (EditText) assignView.findViewById(R.id.editText9);


            //Populate data into template view using data object
            //name.setText(assign.getName());
            //ptsAvail.setText(String.valueOf(assign.getPtsAvail()));

            //****TO DO*** FIgure out how to dynamically pull in points entered.
            // Add listener for edit text
            //whatIfPoints.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            //    @Override
            //    public void onFocusChange(View v, boolean hasFocus) {
            //        if (!hasFocus) {
            //            int itemIndex = v.getId();
            //            String enteredPts = ((EditText) v).getText().toString();
           //             int enteredPoints = Integer.parseInt(enteredPts);
           //             whatIfPts = whatIfPts + enteredPoints;
           //         }
           //     }
           // });

        }
        return assignView;
    }}

    public void getAssignments() {
        new AssignmentsListWhatIf().execute();
    }



    //to pull data from database
    public class AssignmentsListWhatIf extends AsyncTask {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Object doInBackground(Object[] objects) {





                try {
                    HashMap <String, String> params = new HashMap<String, String>();
                    params.put("userID", userID.toString());
                    params.put("courseID", courseID.toString());
                    //?username=testuser&password=password
                    DataUtil dataUtil = new DataUtil("POST","WhatIfAssignments.php?UserID="+userID+
                        "&CourseID=" + courseID);

                        String jsonString = dataUtil.process(null);
                        JSONArray jsonArray = new JSONArray(jsonString);

                        try{
                            if (jsonString != null) {
                                JSONArray arr = new JSONArray(jsonString);


                                for (int i = 0; i<arr.length(); i++){
                                    JSONObject obj = arr.getJSONObject(i);
                                    //Assignments newassign = new Assignments(obj.getString("assignmentID"), userID, courseID, obj.getString("name"), obj.getString("startDate"), obj.getString("dueDate"), obj.getInt("pointsPossible"), 0, obj.getInt("pointsGoal"));
                                    //System.out.print(newassign.getPtsAvail());
                                    //assignments.add(newassign);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                    WhatIfAssignAdapter assignAdapter = new WhatIfAssignAdapter();
                    assignmentList.setAdapter(assignAdapter);
                }
            });
        }




    }
}
