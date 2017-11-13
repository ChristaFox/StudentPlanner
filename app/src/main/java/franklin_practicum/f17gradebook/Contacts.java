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


public class Contacts extends AppCompatActivity {

    private void putArraysInIntent(Intent i) {
        i.putExtra("userID", userID);
        i.putExtra("courseID", courseID);
        i.putExtra("contactID", contactID);
    }

    private void getArraysFromIntent() {
        userID = getIntent().getStringExtra("userID");
        courseID = getIntent().getStringExtra("courseID");
        contactID = getIntent().getStringExtra("contactID");
    }

    public class contact {
        public String contactID, userID, courseID, contactFirstName, contactLastName, contactEmail, contactPhone;

        public contact() {
        }

        public contact(String contactID, String userID, String courseID, String contactFirstName, String contactLastName, String contactEmail, String contactPhone) {
            this.contactID = contactID;
            this.userID = userID;
            this.courseID = courseID;
            this.contactFirstName = contactFirstName;
            this.contactLastName = contactLastName;
            this.contactEmail = contactEmail;
            this.contactPhone = contactPhone;
        }
    }

    private String userID, courseID, contactID;

    public ArrayList<contact> contacts = new ArrayList<>();

    private ImageView addContact;
    ArrayList<contact> contactList = new ArrayList<>();
    ContactListAdapter contactAdapter;
    private ListView contactListView;
    private TextView contactTextView;
    private ImageView deleteContact;
    private Context contactContext;
    private int numContactsLoaded = 0;
    private boolean firstContactLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        getArraysFromIntent();

        contactListView = (ListView) findViewById(R.id.contactListView);
        contactAdapter = new ContactListAdapter(this, contactList);

        contactListView.setAdapter(contactAdapter);

        new FrankContactData().execute();

        contactTextView = (TextView) findViewById(R.id.coursesTextView);

        addContact = (ImageView) findViewById(R.id.addContactButton);
//        addContact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                contactAdapter.add(contactFirstName, contactLastName, contactEmail, contactPhone, contactNotes);
//
//            }
//        });

    }

    public class FrankContactData extends AsyncTask {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                DataUtil dataUtil = new DataUtil("Contacts.php?userid=" + userID);
                String jsonString = dataUtil.process(null);
                //Log.d(TAG, jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);


                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    System.out.println(contacts.get(i).courseID.toString());

                    contactList.add(new Contacts.contact(courseID, userID, jsonObj.getString("contactID"), jsonObj.getString("contactFirstName"), jsonObj.getString("contactLastName"), jsonObj.getString("ContactEmail"), jsonObj.getString("ContactPhone")));
                    System.out.println(contactList.get(i).courseID.toString());
                    numContactsLoaded++;
                }
                return jsonArray;

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
//                    ListView contactListView = (ListView) findViewById(R.id.contactListView);
//                    contactAdapter=new ContactListAdapter(contactListView.getContext(), contactList);
//                    contactListView.setAdapter(contactAdapter);
//                    int length = contacts.size();
//                    for (int i = 0; i < length; i++) {
//                        contactAdapter.add(contacts.get(i).contactFirstName, contacts.get(i).contactLastName,contacts.get(i).contactEmail, contacts.get(i).contactPhone, contacts.get(i).contactNotes);
//                    }
                    int length = contactList.size();
                    int iStart = 0;
                    if (firstContactLoad == false)
                        iStart = 0; //numCoursesLoaded-1;
                    else
                        firstContactLoad = false;
                    for (int i = iStart; i < length; i++) {
                        contactAdapter.add(contactList.get(i).contactFirstName, contactList.get(i).contactLastName, contactList.get(i).contactEmail, contactList.get(i).contactPhone);
                        contactAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    public class FrankInsertContact extends AsyncTask {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {

                DataUtil dataUtil = new DataUtil("POST", "insertContact.php?userID=+userID");

                String jsonString = dataUtil.process(null);

                JSONArray jsonArray = new JSONArray(jsonString);

                String errorOccurred = null;
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    contactID = jsonObj.getString("ContactID");

                }
                return jsonArray;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            contactList.clear();
            contactAdapter.clear();
            contactAdapter.notifyDataSetChanged();
            new FrankContactData().execute();
        }

    }

}