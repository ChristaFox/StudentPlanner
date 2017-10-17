package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Resources extends AppCompatActivity {
    private Button linkToContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
    }

    contacts = (Button) findVeiwById(R.id.linkToContacts);

        contacts.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //if(validEmail && validPassword)
            Intent intent = new Intent(Resources.this, Contacts.class);
            startActivity(intent);
        }
    });
}
