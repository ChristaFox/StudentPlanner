package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Attendance extends AppCompatActivity {

    private void putArraysInIntent(Intent i) {
        i.putExtra("userID", userID);
        i.putExtra("courseID", courseID);
        i.putExtra("attendanceID", attendanceID);
    }

    private void getArraysFromIntent() {
        userID = getIntent().getStringExtra("userID");
        courseID = getIntent().getStringExtra("courseID");
        attendanceID = getIntent().getStringExtra("attendanceID");
    }

    public class attendance {
        public String attendID, userID, courseID, absences, absenceDate;

        public attendance() {
        }

        public attendance(String assignID, String userID, String courseID, String assignName, String assignStartDate, String assignEndDate,
                          String pointsPossible, String pointsEarned, String currentGradeGoal) {
            this.attendID = attendID;
            this.userID = userID;
            this.courseID = courseID;
            this.absences = absences;
            this.absenceDate = absenceDate;
        }
    }

    private String userID, courseID, attendanceID;

    public ArrayList<attendance> absences = new ArrayList<>();

    ArrayList<Object> attendanceList = new ArrayList<Object>();
    AttendanceListAdapter attendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        getArraysFromIntent();

        new FrankAssignData().execute();

        ListView absencesListView = (ListView) findViewById(R.id.absencesListView);
        attendAdapter = new AttendanceListAdapter((absencesListView.getContext()), attendanceList);

        absencesListView.setAdapter(attendAdapter);

        attendAdapter.add(" / / ");
        int length = absences.size();
        for (int i = 0; i < length; i++) {
            attendAdapter.add(absences.get(i).absenceDate);
        }

        ImageView addAbsenceButton = (ImageView) findViewById(R.id.addAbsenceButton);
        addAbsenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendAdapter.add(" / ");
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
                DataUtil dataUtil = new DataUtil("Attendance.php?CourseID=" + courseID + "UserID=" + userID);
                //DataUtil dataUtil = new DataUtil("CourseAssignmentListSelect.php?CourseID="+"6"+"UserID="+"30");
                String jsonString = dataUtil.process(null);
                //Log.d(TAG, jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);

                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    absences.add(new Attendance.attendance());
                    absences.get(i).attendID = jsonObj.getString("attendance ID: ");
                    absences.get(i).absences = jsonObj.getString("total absences: ");
                    absences.get(i).absenceDate = jsonObj.getString("Date: ");

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

