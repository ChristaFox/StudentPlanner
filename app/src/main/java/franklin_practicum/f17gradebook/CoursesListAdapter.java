package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

public class CoursesListAdapter extends ArrayAdapter<Object>{

    //private String userID, courseID, assignmentID;

    private Context activityContext;
    private ArrayList<Courses.course> courseList;
    //private Courses c;
    private static LayoutInflater inflater = null;

    public String assignID, userID, courseID, courseName, courseDesc, courseStartDate,
            courseEndDate, pointsPossible, pointsEarned, currentGradeGoal,
            currentGrade, absencesAllowed, instructorName, numberWeeks, absences;
    private int id;

    public String updateCourseName, updateDesc, updateCourseID;

    public static final String TAG = CoursesListAdapter.class.getSimpleName();

    public CoursesListAdapter(Context context, ArrayList<Courses.course> courseList){
        //super(context, R.layout.activity_courses_list, courseList);
        super(context, R.layout.activity_courses_list);
        this.activityContext = context;
        this.courseList = courseList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //c = new Courses();
    }

    public void add(String courseName, String courseDesc){
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        id++;
        this.add(id);
    }

    public void add(String assignID, String userID, String courseID, String courseName, String courseDesc, String courseStartDate, String courseEndDate,
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
        id++;
        this.add(id);
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ViewHolder holder;
        final Courses.course course = courseList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(activityContext).inflate(
                    R.layout.activity_courses_list, null);
            holder = new ViewHolder();
            holder.courseNameTextView = (TextView) convertView.findViewById(R.id.courseNameTextView);
            holder.descriptionTextView = (TextView)convertView.findViewById(R.id.descriptionTextView);
            holder.archiveImageView = (ImageView)convertView.findViewById(R.id.archiveImageView);
            holder.deleteImageView = (ImageView)convertView.findViewById(R.id.deleteImageView);
            holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);

            //if(!courseName.equals(null)) {
                holder.courseNameTextView.setText(course.courseName);
                holder.descriptionTextView.setText(course.courseDesc);
            //}

            holder.courseNameTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    updateCourseID = course.courseID;
                    updateCourseName = course.courseName;
                    updateDesc = course.courseDesc;
                    new FrankUpdateCourse().execute();
                }
            });

            holder.relativeLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(activityContext, Course.class);
                    intent.putExtra("courseName", course.courseName);
                    intent.putExtra("description", course.courseDesc);
                    intent.putExtra("userID", course.userID);
                    intent.putExtra("courseID", course.courseID);
                    intent.putExtra("assignmentID", course.assignID);
                    intent.putExtra("absences", course.absences);
                    intent.putExtra("absencesAllowed", course.absencesAllowed);
                    intent.putExtra("currentGrade", course.currentGrade);
                    intent.putExtra("currentGradeGoal", course.currentGradeGoal);
                    activityContext.startActivity(intent);
                }
            });


            holder.deleteImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0) {
                    //holder.deleteImageView.setVisibility(View.INVISIBLE);
                    //courseList.remove(courseList.get(position));
                    //Courses.remove(position);
                    update();
                }
            });


            convertView.setTag(holder);
        } else {

           holder = (ViewHolder) convertView.getTag();

        }

        //SET UP THE IMAGES
        //final Object objectPosition = courseList.get(position);

        return convertView;

    }

    public void update()
    {
        this.notifyDataSetChanged();
    }

    private static class ViewHolder{
        TextView courseNameTextView, descriptionTextView;
        ImageView archiveImageView, deleteImageView;
        RelativeLayout relativeLayout;
    }

    public Object getItem(int position){
        return courseList.get(position);
    }


    public class FrankUpdateCourse extends AsyncTask {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                //DataUtil dataUtil = new DataUtil("courseTrial.php");

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("userid", userID);
                //params.put("password", passwordEditText.getText().toString());
                //?username=testuser&password=password
                DataUtil dataUtil = new DataUtil("POST","updateCourse.php?courseid="+updateCourseID+"&name="+updateCourseName+"&desc="+updateDesc);

                System.out.println("updateCourse.php?courseid="+courseID+"&name="+updateCourseName+"&desc="+updateDesc);

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
            //courseList.clear();
            //adapter.clear();
            //adapter.notifyDataSetChanged();
            //new Courses.FrankCourseData().execute();
        }

    }

}
