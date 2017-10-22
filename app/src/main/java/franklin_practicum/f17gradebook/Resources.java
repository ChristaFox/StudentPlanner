package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Resources extends AppCompatActivity {
    private Button contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);


        contacts = (Button) findViewById(R.id.contacts);


        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(validEmail && validPassword)
                Intent intent = new Intent(Resources.this, Contacts.class);
                startActivity(intent);
            }
        });

    }}
