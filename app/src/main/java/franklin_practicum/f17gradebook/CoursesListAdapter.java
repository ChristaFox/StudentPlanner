package franklin_practicum.f17gradebook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

import java.util.List;

public class CoursesListAdapter extends ArrayAdapter<Object> {

    private Context activityContext;
    private List<Object> courseList;

    public static final String TAG = CoursesListAdapter.class.getSimpleName();

    public CoursesListAdapter(Context context, List<Object> courseList){
        super(context, R.layout.activity_courses_list, courseList);
        activityContext = context;
        this.courseList = courseList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        final Object course = courseList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(activityContext).inflate(
                    R.layout.activity_courses_list, null);
            holder = new ViewHolder();
            holder.courseNameTextView = (TextView) convertView.findViewById(R.id.courseNameTextView);
            holder.descriptionTextView = (TextView)convertView.findViewById(R.id.descriptionTextView);
            holder.archiveImageView = (ImageView)convertView.findViewById(R.id.archiveImageView);
            holder.deleteImageView = (ImageView)convertView.findViewById(R.id.deleteImageView);

            /*
            holder.courseNameTextView.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(AdapterView <? > arg, View view, int position, long id) {
                    Intent intent = new Intent(CoursesListAdapter.this, Course.class);
                    startActivity(intent);
                }
            });
            */
            /*
            convertView.setOnClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView <? > arg, View view, int position, long id) {
                    Intent intent = new Intent(Course.class);
                    startActivity(intent);
                }
            });
            */

            /*
            lv.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    AlertDialog.Builder adb=new AlertDialog.Builder(MyActivity.this);
                    adb.setTitle("Delete?");
                    adb.setMessage("Are you sure you want to delete " + position);
                    final int positionToRemove = position;
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            MyDataObject.remove(positionToRemove);
                            adapter.notifyDataSetChanged();
                        }});
                    adb.show();
                }
            });
            */

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        //SET UP THE IMAGES
        final Object objectPosition = courseList.get(position);

        return convertView;

    }

    private static class ViewHolder{
        TextView courseNameTextView, descriptionTextView;
        ImageView archiveImageView, deleteImageView;
    }

    public Object getItem(int position){
        return courseList.get(position);
    }

}
