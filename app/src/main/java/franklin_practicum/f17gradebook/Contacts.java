package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Contacts extends AppCompatActivity {

    private ArrayList<String> contacts, userName, email, phoneNum;

    private void putArraysInIntent(Intent i){
        i.putExtra("userName", userName);
        i.putExtra("email", email);
        i.putExtra("phoneNum", phoneNum);
    }

    public ArrayList<String> getContacts(){
        return contacts;
    }

    public void setContacts(ArrayList<String> updatedContacts){
        contacts = updatedContacts;
    }

    private void getArraysFromIntent(){
        String[] UserNameArr = getIntent().getStringArrayExtra("userName");
        String[] emailArr = getIntent().getStringArrayExtra("email");
        String[] phoneNumArr = getIntent().getStringArrayExtra("phoneNum");
        for(String s: UserNameArr){userName.add(s);}
        for(String s: emailArr){email.add(s);}
        for(String s: phoneNumArr){phoneNum.add(s);}
    }

    ArrayList<Object> contactList=new ArrayList<Object>();
//    ContactsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
    }
//
//    EditText updatedContacts = (EditText) findViewById(R.id.listView);
//    contactsAdapter=new ContactsListAdapter(list.getContext(), assignmentList);
//
//        list.setAdapter(adapter);
//    Button addContactButton = (Button) findViewById(R.id.addContactButton);
//        addContactButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(Assignments.this, WhatIf.class);
            //putArraysInIntent(intent);
//            startActivity(intent);
}
