package franklin_practicum.f17gradebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Courses extends AppCompatActivity {

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

    public class course{
        public String assignID, userID, courseID, courseName, courseDesc, courseStartDate,
                courseEndDate, pointsPossible, pointsEarned, currentGradeGoal,
                currentGrade, absencesAllowed, instructorName, numberWeeks, absences;
        public course(){}
        public course(String assignID, String userID, String courseID, String courseName, String courseDesc, String courseStartDate, String courseEndDate,
                      String pointsPossible, String pointsEarned, String currentGradeGoal, String currentGrade, String absencesAllowed, String instructorName,
                      String numberWeeks, String absences){
            this.assignID = assignID;
            this.userID = userID;
            this.courseID = courseID;
            this.courseName = courseName;
            this.courseDesc = courseDesc;
            this.courseStartDate = courseStartDate;
            this.courseEndDate = courseEndDate;
            this.pointsPossible = pointsPossible;
            this.pointsEarned = pointsEarned;
            this.currentGradeGoal = currentGradeGoal;
            this.currentGrade = currentGrade;
            this.absencesAllowed = absencesAllowed;
            this.instructorName = instructorName;
            this.numberWeeks = numberWeeks;
            this.absences = absences;
        }
    }

    private String userID, courseID, assignmentID;

    public ArrayList<course> courses = new ArrayList<course>();

    private ImageView addCourse;
    ArrayList<course> courseList=new ArrayList<course>();
    CoursesListAdapter adapter;
    private ListView list;
    private ImageView deleteButton;
    private TextView coursesTextView;
    private ImageButton resources;

    private Context context;

    private int numCoursesLoaded = 0;
    private boolean firstCourseLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        getArraysFromIntent();

        ListView list = (ListView) findViewById(R.id.courseListView);
        adapter=new CoursesListAdapter(this, courseList);

        list.setAdapter(adapter);

        new FrankCourseData().execute();

        coursesTextView = (TextView) findViewById(R.id.coursesTextView);

        resources = (ImageButton) findViewById(R.id.resources);
        resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this, Resources.class);

                putArraysInIntent(intent);
                startActivity(intent);
            }
        });

        addCourse = (ImageView) findViewById(R.id.addCourse);
        /*
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FrankInsertCourse().execute();
            }
        });
        */

    }

    protected void remove(int pos){
        adapter.remove(adapter.getItem(pos));
    }

    public class FrankCourseData extends AsyncTask {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                DataUtil dataUtil = new DataUtil("courses.php?userid="+userID);
                String jsonString = dataUtil.process(null);
                //Log.d(TAG, jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);


                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    courseList.add(new course(assignmentID, userID, jsonObj.getString("id"), jsonObj.getString("course"), jsonObj.getString("description"), jsonObj.getString("date"), "",
                            "", "", jsonObj.getString("gradegoal"), jsonObj.getString("currentgrade"), jsonObj.getString("absencesallowed"), "", "",jsonObj.getString("absences")));
                    System.out.println(courseList.get(i).courseID.toString());
                    numCoursesLoaded++;

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

                    //adapter.add(courses);
                    int length = courseList.size();
                    int iStart = 0;
                    if(firstCourseLoad == false)
                        iStart = 0; //numCoursesLoaded-1;
                    else
                        firstCourseLoad = false;
                    for (int i = iStart; i < length; i++) {
                        adapter.add(courseList.get(i).courseName, courseList.get(i).courseDesc);
                        adapter.notifyDataSetChanged();
                    }

                }
            });
        }
    }

    public class FrankInsertCourse extends AsyncTask {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {

                DataUtil dataUtil = new DataUtil("POST","insertCourse.php?userid="+userID);

                String jsonString = dataUtil.process(null);

                JSONArray jsonArray = new JSONArray(jsonString);

                String errorOccurred = null;
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    courseID = jsonObj.getString("courseid");

                }
                return jsonArray;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(Object o){
            courseList.clear();
            adapter.clear();
            adapter.notifyDataSetChanged();
            new FrankCourseData().execute();
        }

    }

}
