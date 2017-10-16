package franklin_practicum.f17gradebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Authentication extends AppCompatActivity {

    private Button login, register;
    private ImageView header;
    private String screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setMainScreen();
    }

    public void setMainScreen()
    {
        setContentView(R.layout.activity_authentication);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        header = (ImageView) findViewById(R.id.header);
        screen = "main";

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_authentication_login);
                screen = "login";
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_authentication_register);
                screen = "register";
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(screen.equals("main"))
        {
            super.onBackPressed();
        }
        else
        {
            setMainScreen();
        }
    }
    /*
    //@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:

            break;
        }
    }
    */
}
