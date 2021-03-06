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

import java.util.ArrayList;

import java.util.List;

public class AssignmentsListAdapter extends ArrayAdapter<Object>{

    private Context activityContext;
    private ArrayList<Assignments.assignment> assignmentsList;

    private EditText dateEditBox, dueEditBox, assignment;
    private static LayoutInflater inflater = null;

    public String assignmentName, dueDate, grade;
    private int id;

    public static final String TAG = CoursesListAdapter.class.getSimpleName();

    public AssignmentsListAdapter(Context context, ArrayList<Assignments.assignment> assignmentsList){
        //super(context, R.layout.activity_assignments_list, assignmentsList);
        super(context, R.layout.activity_assignments_list);
        this.activityContext = context;
        this.assignmentsList = assignmentsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void add(String assignmentName, String dueDate, String grade){
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
        this.grade = grade;
        id++;
        this.add(id);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ViewHolder holder;
        final Assignments.assignment assign = assignmentsList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(activityContext).inflate(
                    R.layout.activity_assignments_list_item, null);

            /*
            holder = new ViewHolder();

            holder.dateEditBox = (EditText) convertView.findViewById(R.id.dateEditBox);
            holder.gradeEditBox = (EditText) convertView.findViewById(R.id.dueEditBox);
            holder.assignmnent = (EditText) convertView.findViewById(R.id.assignment);

            holder.dateEditBox.setText(assign.assignEndDate);
            holder.gradeEditBox.setText(assign.pointsEarned + "/" + assign.pointsPossible);
            holder.assignmnent.setText(assign.assignName);

            holder.deleteImageView = (ImageView) convertView.findViewById(R.id.delete);

            holder.deleteImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0) {
                    //holder.deleteImageView.setVisibility(View.INVISIBLE);
                    assignmentsList.remove(position);
                    update();
                }
            });
            */
            holder = new ViewHolder();

        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        holder.dateEditBox = (TextView) convertView.findViewById(R.id.dateEditBox);
        holder.gradeEditBox = (TextView) convertView.findViewById(R.id.dueEditBox);
        holder.assignmnent = (TextView) convertView.findViewById(R.id.assignment);

        holder.dateEditBox.setText(assign.assignEndDate);
        holder.gradeEditBox.setText(assign.pointsEarned + "/" + assign.pointsPossible);
        holder.assignmnent.setText(assign.assignName);

        holder.deleteImageView = (ImageView) convertView.findViewById(R.id.delete);

        holder.deleteImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0) {
                //holder.deleteImageView.setVisibility(View.INVISIBLE);
                assignmentsList.remove(position);
                update();
            }
        });

        convertView.setTag(holder);

        return convertView;

    }

    public void update()
    {
        this.notifyDataSetChanged();
    }

    private static class ViewHolder{
        ImageButton addAssignment;
        ListView listView;
        ImageView deleteImageView;
        TextView assignmnent, dateEditBox, gradeEditBox;
    }

    public Object getItem(int position){
        return assignmentsList.get(position);
    }

}
