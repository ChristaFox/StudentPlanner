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

public class ResourcesListAdapter extends ArrayAdapter<Object>{

    private String userID, courseID, assignmentID;

    private Context resourceContext;
    private ArrayList<Resources.resource> resourceList;
    private Resources resource;
    private int resourceID;
    public String resourceName;
    public String website;

    public static final String TAG = ResourcesListAdapter.class.getSimpleName();

    public ResourcesListAdapter(Context context, ArrayList<Resources.resource> resourceList){
        super(context, R.layout.activity_resources_list_item);
        resourceContext = context;
        this.resourceList = resourceList;
        resource = new Resources();
    }

    public void add(String resourceName, String website){
        this.resourceName=resourceName;
        this.website=website;
        resourceID++;
        this.add(resourceID);
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ViewHolder holder;
        final Resources.resource resource = resourceList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(resourceContext).inflate(
                    R.layout.activity_resources_list_item, null);
            holder = new ViewHolder();
            holder.resourceNameTextView = (TextView)convertView.findViewById(R.id.resourceName);
            holder.websiteTextView = (TextView)convertView.findViewById(R.id.website);
           // holder.archiveImageView = (ImageView)convertView.findViewById(R.id.archiveImageView);
           // holder.deleteImageView = (ImageView)convertView.findViewById(R.id.deleteImageView);
           // holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);

            //if(!courseName.equals(null)) {
            holder.resourceNameTextView.setText(resourceName);
            holder.websiteTextView.setText(website);

            //}

    /*
            holder.resourceNameTextView.addTextChangedListener(new TextWatcher() {
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
                    Intent intent = new Intent(resourceContext.getApplicationContext(), Resources.class);
                    intent.putExtra("resourceNameTextView", holder.resourceNameTextView.getText().toString());
                    resourceContext.startActivity(intent);
                }
            });


            holder.deleteImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0) {
                    //holder.deleteImageView.setVisibility(View.INVISIBLE);
                    resourceList.remove(position);
                    update();
                }
            });

*/
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        //SET UP THE IMAGES
        final Object objectPosition = resourceList.get(position);

        return convertView;

    }

    public void update()
    {
        this.notifyDataSetChanged();
    }

    private static class ViewHolder{
        ListView resourcesListView;
        ImageView archiveImageView, deleteImageView;
        RelativeLayout relativeLayout;
        TextView resourceNameTextView, websiteTextView;
    }

    public Object getItem(int position){
        return resourceList.get(position);
    }

}

