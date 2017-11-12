package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;

public class ContactListAdapter extends ArrayAdapter<Object>{

    private String userID, courseID;

    private Context contactContext;
    private List<Object> contactList;
    private Contacts con;
    public String contactFirstName, contactLastName, contactEmail, contactPhone, contactNotes;
    private int contactID;

    public static final String TAG = ContactListAdapter.class.getSimpleName();

    public ContactListAdapter(Context contactContext, List<Object> contactList){
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
            holder.contactFirstNameTextView = (TextView) convertView.findViewById(R.id.contactFirstNameTextView);
            holder.contactLastNameTextView = (TextView)convertView.findViewById(R.id.contactLastNameTextView);
            holder.contactEmailTextView = (TextView)convertView.findViewById(R.id.contactEmailTextView);
            holder.contactPhoneNumTextView = (TextView)convertView.findViewById(R.id.contactPhoneNumTextView);
            holder.archiveImageView = (ImageView)convertView.findViewById(R.id.archiveImageView);
            holder.deleteImageView = (ImageView)convertView.findViewById(R.id.deleteImageView);
            holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);

            holder.contactFirstNameTextView.setText(contactFirstName);
            holder.contactLastNameTextView.setText(contactLastName);
            holder.contactEmailTextView.setText(contactEmail);
            holder.contactPhoneNumTextView.setText(contactPhone);

            holder.contactFirstNameTextView.addTextChangedListener(new TextWatcher() {
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
            });

            holder.relativeLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(contactContext.getApplicationContext(), Contacts.class);
                    intent.putExtra("first name", holder.contactFirstNameTextView.getText().toString());
                    intent.putExtra("last name", holder.contactLastNameTextView.getText().toString());
                    intent.putExtra("email", holder.contactEmailTextView.getText().toString());
                    intent.putExtra("phone number", (CharSequence) holder.contactPhoneNumTextView).getCharSequenceExtra("").toString();
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

    private class ViewHolder{
        TextView contactFirstNameTextView, contactLastNameTextView, contactEmailTextView, contactPhoneNumTextView;
        ImageView archiveImageView, deleteImageView;
        RelativeLayout relativeLayout;
    }

    public Object getItem(int position){
        return contactList.get(position);
    }

}

