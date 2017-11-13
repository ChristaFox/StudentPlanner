package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactListAdapter extends ArrayAdapter<Object>{

    //private String userID, courseID;

    private Context contactContext;
    private ArrayList<Contacts.contact> contactList;
    //private Contacts con;
    private static LayoutInflater inflater = null;
    public String contactID, userID, courseID, contactFirstName, contactLastName, contactEmail, contactPhone, contactNotes;
    private int intID;
    public String updateContactID, updateContactFirstName, updateContactLastName, updateContactEmail, UpdateContactPhone;
    public static final String TAG = ContactListAdapter.class.getSimpleName();

    public ContactListAdapter(Context contactContext, ArrayList<Contacts.contact> contactList){
        super(contactContext, R.layout.activity_contact_list);
        this.contactContext = contactContext;
        this.contactList = contactList;
        inflater = (LayoutInflater) contactContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //con = new Contacts();
    }

    public void add(String contactFirstName, String contactLastName, String contactEmail, String contactPhone) {
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        intID++;
        this.add(intID);
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ViewHolder holder;
        final Contacts.contact contact = contactList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(contactContext).inflate(
                    R.layout.activity_contacts_list_item, null);
            holder = new ViewHolder();
            holder.contactFirstNameTextView = (TextView) convertView.findViewById(R.id.contactFirstNameTextView);
            holder.contactLastNameTextView = (TextView) convertView.findViewById(R.id.contactLastNameTextView);
            holder.contactEmailTextView = (TextView) convertView.findViewById(R.id.contactEmailTextView);
            holder.contactPhoneTextView = (TextView) convertView.findViewById(R.id.contactPhoneNumTextView);
            holder.deleteImageView = (ImageView)convertView.findViewById(R.id.deleteImageView);
            holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);
            holder.contactFirstNameTextView.setText(contactFirstName);
            holder.contactLastNameTextView.setText(contactLastName);
            holder.contactEmailTextView.setText(contactEmail);
            holder.contactPhoneTextView.setText(contactPhone);

            /*holder.contactFirstNameTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {

                    new FrankUpdateCourse().execute();
                }
            });

            holder.contactLastNameTextView.addTextChangedListener(new TextWatcher() {
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

            holder.contactEmailTextView.addTextChangedListener(new TextWatcher() {
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

            holder.contactPhoneNumTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });*/

            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(contactContext.getApplicationContext(), Contacts.class);
                    intent.putExtra("firstName", contact.contactFirstName);
                    intent.putExtra("lastName", contact.contactLastName);
                    intent.putExtra("email", contact.contactEmail);
                    intent.putExtra("phone", contact.contactPhone);
                    contactContext.startActivity(intent);
                }
            });


            holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    //holder.deleteImageView.setVisibility(View.INVISIBLE);
                    //contactList.remove(position);
                    update();
                }
            });


            convertView.setTag(holder);

        }

        return convertView;
    }

    public void update() {
        this.notifyDataSetChanged();
    }

    private static class ViewHolder{
        TextView contactFirstNameTextView, contactLastNameTextView, contactEmailTextView, contactPhoneTextView;
        RelativeLayout relativeLayout;
        ImageView deleteImageView;
    }

    public Object getItem(int position){
        return contactList.get(position);
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

                DataUtil dataUtil = new DataUtil("POST","UpdateContact.php?contactID="+updateContactID+"&firstName="+updateContactFirstName+"&lastName="+updateContactLastName+"&email="+updateContactEmail+"&phone="+UpdateContactPhone);

                System.out.println("updateContact.php?course="+courseID+"&contactID"+updateContactID+"&firstName="+updateContactFirstName+"&lastName="+updateContactLastName+"&email="+updateContactEmail+"&phone="+UpdateContactPhone);

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
        protected void onPostExecute(Object o){
            //contactList.clear();
            //contactAdapter.clear();
            //ContactAdapter.notifyDataSetChanged();
            //new Cantacts.FrankCourseData().execute();
        }

    }

}


