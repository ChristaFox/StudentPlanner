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
        public String assignID, userID, courseID, courseName, courseDesc, courseStartDate, courseEndDate, pointsPossible, pointsEarned, currentGradeGoal;
        public course(){}
        public course(String assignID, String userID, String courseID, String courseName, String courseDesc, String courseStartDate, String courseEndDate,
                      String pointsPossible, String pointsEarned, String currentGradeGoal){
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
        }
    }

    private String userID, courseID, assignmentID;

    public ArrayList<course> courses = new ArrayList<course>();

    private ImageView addCourse;
    ArrayList<Object> courseList=new ArrayList<Object>();
    CoursesListAdapter adapter;
    private ListView list;
    private ImageView deleteButton;
    private TextView coursesTextView;
    private ImageButton resources;

    private Context context;

    private int numCoursesLoaded = 0;
    private boolean firstCourseLoad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        getArraysFromIntent();

        ListView list = (ListView) findViewById(R.id.courseListView);
        adapter=new CoursesListAdapter(list.getContext(), courseList);
        //adapter.add(0);
        //adapter.remove(0);

        list.setAdapter(adapter);

        Toast.makeText(getApplicationContext(), "userID: "+userID, Toast.LENGTH_LONG).show();

        new FrankCourseData().execute();

        /*
        ListView lv = (ListView) findViewById(R.id.listView);
        FriendsListAdapter adapter = new FriendsListAdapter(lv.getContext(), friendsObjects);
        lv.setAdapter(adapter);
        */


        //adapter.add(userID, String.valueOf(courses.size()));

        /*
        TextView  et0 = (TextView ) list.getChildAt(0).findViewById(R.id.courseNameTextView);
        et0.setText("TEST COURSE NAME");
        et0 = (TextView ) list.getChildAt(0).findViewById(R.id.descriptionTextView);
        et0.setText("TEST COURSE DESC");
        */


        coursesTextView = (TextView) findViewById(R.id.coursesTextView);
        /*
        coursesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this, Course.class);
                startActivity(intent);
            }
        });
        */

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
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapter.add("Course Name","Description");
                new FrankInsertCourse().execute();

            }

            /*
            //@Override
            public void onItemClick(AdapterView<?> parent, View  view, int position, long id){
                Intent intent = new Intent(Courses.this, Course.class);
                startActivity(intent);
            }
            */
        });

        /*
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <? > arg, View view, int position, long id) {
                Intent intent = new Intent(Courses.this, Course.class);
                startActivity(intent);
            }
        });
        */

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

                    if(i >= numCoursesLoaded){
                        courses.add(new course("", userID, jsonObj.getString("id"), jsonObj.getString("course"), jsonObj.getString("description"), jsonObj.getString("date"), "",
                                "", "", ""));
                        //courses.get(i).courseID = jsonObj.getString("id");
                        //courses.get(i).courseName = jsonObj.getString("course");
                        //courses.get(i).courseStartDate = jsonObj.getString("date");
                        //courses.get(i).courseDesc = jsonObj.getString("description");
                        //courses.add(new course(jsonObj.getString("course"), ));
                        //subItems.add("start date: " + jsonObj.getString("date"));
                        //expandableListDetail.put(jsonObj.getString("course"), subItems);
                        System.out.println(courses.get(i).courseID.toString());
                        numCoursesLoaded++;
                    }
                }
                return jsonArray;

                /*

        while( $row = $res->fetch_assoc() )

            array_push($result, array('course' => $row["CourseName"],

                          'date' => $row["CourseStartDate"], 'id' => $row["CourseID"], 'userID' => $row["UserID"]));

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

                    //adapter.add(courses);
                    int length = courses.size();
                    int iStart = 0;
                    if(firstCourseLoad == false)
                        iStart = numCoursesLoaded-1;
                    else
                        firstCourseLoad = false;
                    for (int i = iStart; i < length; i++) {
                        adapter.add(courses.get(i).courseName, courses.get(i).courseDesc);
                    }


                    //expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                    //expandableListAdapter = new CustomExpandableListAdapter(CourseActivity.this, expandableListTitle, expandableListDetail);
                    //expandableListView.setAdapter(expandableListAdapter);
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
                //DataUtil dataUtil = new DataUtil("courseTrial.php");

                HashMap <String, String> params = new HashMap<String, String>();
                params.put("userid", userID);
                //params.put("password", passwordEditText.getText().toString());
                //?username=testuser&password=password
                DataUtil dataUtil = new DataUtil("POST","insertCourse.php?userid="+userID);

                String jsonString = dataUtil.process(null);
                //Log.d(TAG, jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);

                String errorOccurred = null;
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    //if(errorOccurred.equals(null))
                    //    errorOccurred = jsonObj.getString("error");
                    //if(!errorOccurred.equals(null))
                    //    return "Error";
                    courseID = jsonObj.getString("userid");

                }
                return jsonArray;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(Object o){
            new FrankCourseData().execute();
        }

    }

}
