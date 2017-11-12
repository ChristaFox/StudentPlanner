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

import java.util.ArrayList;

import java.util.List;

public class AttendanceListAdapter extends ArrayAdapter<Object>{

    private String userID, courseID, assignmentID;

    private Context attendContext;
    private List<Object> attendList;
    private Attendance attend;
    private EditText dateOfAbsenceTextView;
    private int attendID;
    public String dateOfAbsence;

    public static final String TAG = AttendanceListAdapter.class.getSimpleName();

    public AttendanceListAdapter(Context context, List<Object> attendList){
        super(context, R.layout.activity_attendance_list_item, attendList);
        attendContext = context;
        this.attendList = attendList;
        attend = new Attendance();
    }

    public void add(String dateOfAbsence){
        this.dateOfAbsence=dateOfAbsence;
        attendID++;
        this.add(attendID);
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ViewHolder holder;
        final Object attend = attendList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(attendContext).inflate(
                    R.layout.activity_attendance, null);
            holder = new ViewHolder();
            holder.dateOfAbsenceTextView = (TextView) convertView.findViewById(R.id.dateOfAbsence);
            holder.archiveImageView = (ImageView)convertView.findViewById(R.id.archiveImageView);
            holder.deleteImageView = (ImageView)convertView.findViewById(R.id.deleteImageView);
            holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);

            //if(!courseName.equals(null)) {
            holder.dateOfAbsenceTextView.setText(dateOfAbsence);
            //}

            holder.dateOfAbsenceTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            holder.relativeLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(attendContext.getApplicationContext(), Attendance.class);
                    intent.putExtra("dateOfAbsence", holder.dateOfAbsenceTextView.getText().toString());
                    attendContext.startActivity(intent);
                }
            });


            holder.deleteImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0) {
                    //holder.deleteImageView.setVisibility(View.INVISIBLE);
                    attendList.remove(position);
                    update();
                }
            });


            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        //SET UP THE IMAGES
        final Object objectPosition = attendList.get(position);

        return convertView;

    }

    public void update()
    {
        this.notifyDataSetChanged();
    }

    private static class ViewHolder{
        TextView dateOfAbsenceTextView;
        ImageView archiveImageView, deleteImageView;
        RelativeLayout relativeLayout;
    }

    public Object getItem(int position){
        return attendList.get(position);
    }

}
