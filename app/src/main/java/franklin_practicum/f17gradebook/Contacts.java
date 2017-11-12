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

    private void putArraysInIntent(Intent i){
        i.putExtra("userID", userID);
        i.putExtra("courseID", courseID);
        i.putExtra("contactID", contactID);
    }

    private void getArraysFromIntent(){
        userID = getIntent().getStringExtra("userID");
        courseID = getIntent().getStringExtra("courseID");
        contactID = getIntent().getStringExtra("contactID");
    }

    public class contact{
        public String contactID, userID, courseID, contactFirstName, contactLastName, contactEmail, contactPhone, contactNotes;
        public contact(){}
        public contact(String contactID, String userID, String courseID, String contactFirstName, String contactLastName, String contactEmail, String contactPhone,
                      String contactNotes){
            this.contactID = contactID;
            this.userID = userID;
            this.courseID = courseID;
            this.contactFirstName = contactFirstName;
            this.contactLastName = contactLastName;
            this.contactEmail = contactEmail;
            this.contactPhone = contactPhone;
            this.contactNotes = contactNotes;
        }
    }

    private String userID, courseID, contactID;

    public ArrayList<contact> contacts = new ArrayList<>();

    private ImageView addContact;
    ArrayList<Object> contactList=new ArrayList<Object>();
    ContactListAdapter contactAdapter;
    private ListView contactListView;
    private ImageView deleteContact;
    private TextView contactTextView;
    private Context contactContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        getArraysFromIntent();

        Toast.makeText(getApplicationContext(), "userID: "+userID, Toast.LENGTH_LONG).show();

        new FrankCourseData().execute();

        contactTextView = (TextView) findViewById(R.id.contacts);

        addContact = (ImageView) findViewById(R.id.addContactButton);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactAdapter.add(contactFirstName, contactLastName, contactEmail, contactPhone, contactNotes);

            }
        });

    }

    public class FrankCourseData extends AsyncTask {
        @Override
        protected void onPreExecute() { }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                DataUtil dataUtil = new DataUtil("Contacts.php?userid="+userID);
                String jsonString = dataUtil.process(null);
                //Log.d(TAG, jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);


                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    //Contacts.add(new contact("", userID, jsonObj.getString("id"), "", courseID, jsonObj.getString("course"),
                    //        "", contactID, jsonObj.getString("ContactID"), "", "","", "", ""));
                    Contacts.get(i).courseID = jsonObj.getString("course id");
                    Contacts.get(i).contactFirstName = jsonObj.getString("first name");
                    Contacts.get(i).contactLastName = jsonObj.getString("last name");
                    Contacts.get(i).contactEmail = jsonObj.getString("email");
                    Contacts.get(i).contactPhone = jsonObj.getString("phone number");
                    Contacts.get(i).contactNotes = jsonObj.getString("notes");

                    System.out.println(contacts.get(i).courseID.toString());
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
                    ListView contactListView = (ListView) findViewById(R.id.contactListView);
                    contactAdapter=new ContactListAdapter(contactListView.getContext(), contactList);
                    contactListView.setAdapter(contactAdapter);
                    int length = contacts.size();
                    for (int i = 0; i < length; i++) {
                        contactAdapter.add(contacts.get(i).contactFirstName, contacts.get(i).contactLastName,contacts.get(i).contactEmail, contacts.get(i).contactPhone, contacts.get(i).contactNotes);
                    }

                }
            });
        }
    }
}