package franklin_practicum.f17gradebook;

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

    private ImageView addCourse;
    ArrayList<Object> courseList=new ArrayList<Object>();
    CoursesListAdapter adapter;
    private ListView list;
    private ImageView deleteButton;
    private TextView coursesTextView;
    private ImageButton linkToResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        /*
        ListView lv = (ListView) findViewById(R.id.listView);
        FriendsListAdapter adapter = new FriendsListAdapter(lv.getContext(), friendsObjects);
        lv.setAdapter(adapter);
        */
        ListView list = (ListView) findViewById(R.id.courseListView);
        adapter=new CoursesListAdapter(list.getContext(), courseList);

        list.setAdapter(adapter);

        coursesTextView = (TextView) findViewById(R.id.coursesTextView);
        coursesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this, Course.class);
                startActivity(intent);
            }
        });

        linkToResources = (ImageButton) findViewById(R.id.linkToResources);
        linkToResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this, Resources.class);
                startActivity(intent);
            }
        });

        addCourse = (ImageView) findViewById(R.id.addCourse);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("Test");
                /*
                final int adapterSize = adapter.getCount();
                deleteButton = ((View) list.).(ImageView) findViewById(R.id.deleteImageView);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.remove(adapter.getItem(adapterSize));
                    }
                });
                */
            }

            //@Override
            public void onItemClick(AdapterView<?> parent, View  view, int position, long id){
                Intent intent = new Intent(Courses.this, Course.class);
                startActivity(intent);
            }
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
