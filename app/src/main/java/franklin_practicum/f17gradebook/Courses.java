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

public class Courses extends AppCompatActivity {

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

    public ArrayList<String> getCourses(){
        return courses;
    }

    public void setCourses(ArrayList<String> newList){
        courses = newList;
    }


    private ImageView addCourse;
    ArrayList<Object> courseList=new ArrayList<Object>();
    CoursesListAdapter adapter;
    private ListView list;
    private ImageView deleteButton;
    private TextView coursesTextView;
    private ImageButton resources;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        //getArraysFromIntent();

        /*
        ListView lv = (ListView) findViewById(R.id.listView);
        FriendsListAdapter adapter = new FriendsListAdapter(lv.getContext(), friendsObjects);
        lv.setAdapter(adapter);
        */
        ListView list = (ListView) findViewById(R.id.courseListView);
        adapter=new CoursesListAdapter(list.getContext(), courseList);

        list.setAdapter(adapter);

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
                //putArraysInIntent(intent);
                startActivity(intent);
            }
        });

        addCourse = (ImageView) findViewById(R.id.addCourse);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("Test");

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


}
