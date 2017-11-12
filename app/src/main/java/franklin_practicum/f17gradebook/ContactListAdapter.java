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

public class ContactsListAdapter extends ArrayAdapter<Object>{

    private String userID, courseID;

    private Context contactContext;
    private List<Object> contactList;
    private Contacts con;
    public String contactFirstName, contactLastName, contactEmail, contactPhone, contactNotes;
    private int contactID;

    public static final String TAG = ContactsListAdapter.class.getSimpleName();

    public ContactsListAdapter(Context contactContext, List<Object> contactList){
        super(contactContext, R.layout.activity_contact_list, contactList);
        contactContext = contactContext;
        this.contactList = contactList;
        con = new Contacts();
    }

    public void add(String courseName, String courseDesc){
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.contactNotes = contactNotes;
        contactID++;
        this.add(contactID);
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ViewHolder holder;
        final Object contact = contactList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(contactContext).inflate(
                    R.layout.activity_contact_list, null);
            holder = new ViewHolder();
            holder.contactFirstNameTextVeiw = (TextView) convertView.findViewById(R.id.contactFirstNameTextVeiw);
            holder.contactLastNameTextView = (TextView)convertView.findViewById(R.id.contactLastNameTextVeiw);
            holder.contactEmailTextView = (TextView)convertView.findViewById(R.id.contactEmailTextVeiw);
            holder.contactPhoneNumTextView = (TextView)convertView.findViewById(R.id.contactPhoneNumTextVeiw);
            holder.archiveImageView = (ImageView)convertView.findViewById(R.id.archiveImageView);
            holder.deleteImageView = (ImageView)convertView.findViewById(R.id.deleteImageView);
            holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);

            holder.contactFirstNameTextView.setText(contactFirstName);
            holder.contactLastNameTextView.setText(contactLastName);
            holder.contactEmailTextView.setText(contactEmail);
            holder.contactPhoneNumTextView.setText(contactPhone);

            holder.contactNameTextView.addTextChangedListener(new TextWatcher() {
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
                    Intent intent = new Intent(contactContext.getApplicationContext(), Contacts.class);
                    intent.putExtra("first name", holder.contactFirstNameTextView.getText().toString());
                    intent.putExtra("last name", holder.contactLastNameTextView.getText().toString());
                    intent.putExtra("email", holder.contactEmailTextView.getText().toString());
                    intent.putExtra("phone number", holder.contactPhoneNumTextView).getText().toString();
                    contactContext.startActivity(intent);
                }
            });


            holder.deleteImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0) {
                    //holder.deleteImageView.setVisibility(View.INVISIBLE);
                    contactList.remove(position);
                    update();
                }
            });


            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        //SET UP THE IMAGES
        final Object objectPosition = contactList.get(position);

        return convertView;

    }

    public void update()
    {
        this.notifyDataSetChanged();
    }

    private static class ViewHolder{
        TextView contactFirstNameTextVeiw, contactLastNameTextView, contactEmailTextView, contactPhoneNumTextView;
        ImageView archiveImageView, deleteImageView;
        RelativeLayout relativeLayout;
    }

    public Object getItem(int position){
        return contactList.get(position);
    }

}

