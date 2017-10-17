package franklin_practicum.f17gradebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Authentication extends AppCompatActivity {

    private Button login, register;
    private ImageView header;
    private String screen;
    private EditText emailText, passwordText, passwordConfrim;

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
                setLoginScreen();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRegisterScreen();
            }
        });
    }

    public void setLoginScreen()
    {
        setContentView(R.layout.activity_authentication_login);
        screen = "login";
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(validEmail && validPassword)
                Intent intent = new Intent(Authentication.this, Courses.class);
                startActivity(intent);
            }
        });
    }

    public void setRegisterScreen()
    {
        setContentView(R.layout.activity_authentication_register);
        screen = "register";
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(validEmail && validPassword)
                Intent intent = new Intent(Authentication.this, Courses.class);
                startActivity(intent);
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
