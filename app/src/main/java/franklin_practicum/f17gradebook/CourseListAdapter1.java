package franklin_practicum.f17gradebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by LarryXu on 11/9/2017.
 */

public class CourseListAdapter1 extends ArrayAdapter<CourseEntity> {

    private Activity context;
    private String userID;

    public CourseListAdapter1(String userID, Activity context, ArrayList<CourseEntity> courses) {
        super(context, 0, courses);
        this.context = context;
        this.userID = userID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        final CourseEntity course = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_courses_list, parent, false);
        }

        ImageView btnArchive = (ImageView) convertView.findViewById(R.id.archiveImageView);
        btnArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Course.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("userID", userID);
                intent.putExtra("courseID", course.courseID);
                intent.putExtra("courseName",course.courseName);
                v.getContext().startActivity(intent);
            }
        });
        // Lookup view for data population
        EditText courseName = (EditText) convertView.findViewById(R.id.courseNameTextView);
        EditText courseDesc = (EditText) convertView.findViewById(R.id.descriptionTextView);
        // Populate the data into the template view using the data object
        courseName.setText(course.courseName);
        courseDesc.setText(course.courseDesc);
        return convertView;
    }
}
