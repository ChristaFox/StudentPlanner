package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Contacts extends AppCompatActivity {

    private ArrayList<String> userName, email, phoneNum;

    private void putArraysInIntent(Intent i){
        i.putExtra("userName", userName);
        i.putExtra("email", email);
        i.putExtra("phoneNum", phoneNum);
    }

    public ArrayList<String> getContacts(){
        return contacts;
    }

    public void setContacts(ArrayList<String> newList){
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
    }
}
