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

import java.util.ArrayList;
import java.util.HashMap;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.text.method.PasswordTransformationMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class Authentication extends AppCompatActivity {

    private void putArraysInIntent(Intent i){
        i.putExtra("userID", userID);
        i.putExtra("courseID", courseID);
        i.putExtra("assignmentID", assignmentID);
    }

    private void getArraysFromIntent(){
        userID = getIntent().getStringExtra("userID");
        courseID = getIntent().getStringExtra("courseID");
        assignmentID = getIntent().getStringExtra("assignmentID");
    }

    private String userID, courseID, assignmentID;

    private Button login, register;
    private ImageView header;
    private String screen, errorOccured;
    private EditText emailEditText, passwordEditText, confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getArraysFromIntent();
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

        emailEditText = (EditText) findViewById(R.id.emailText);
        passwordEditText = (EditText) findViewById(R.id.passwordText);

        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new FrankLoginUser().execute();
                
            }
        });
    }

    public void setRegisterScreen()
    {
        setContentView(R.layout.activity_authentication_register);
        screen = "register";
        register = (Button) findViewById(R.id.register);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(validEmail && validPassword)
                Boolean success = true;
                if (emailEditText.getText().toString().length() == 0)
                {
                    emailEditText.setError("Please enter an email");
                    success = false;
                }
                if (!(emailEditText.getText().toString().contains("@")))
                {
                    emailEditText.setError("Please enter a valid email");
                    success = false;
                }
                if (passwordEditText.getText().toString().length() == 0)
                {
                    passwordEditText.setError("Please enter a password");
                    success = false;
                }
                if (!(confirmPasswordEditText.getText().toString().equals(passwordEditText.getText().toString())))
                {
                    confirmPasswordEditText.setError("Passwords do not match");
                    success = false;
                }
                if(success) {

                    new FrankRegisterUser().execute();

                    Intent intent = new Intent(Authentication.this, Courses.class);
                    putArraysInIntent(intent);
                    startActivity(intent);

                }
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


    public class FrankRegisterUser extends AsyncTask {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                //DataUtil dataUtil = new DataUtil("courseTrial.php");

                if (emailEditText.getText().toString().equals(null) || emailEditText.getText().toString().equals("")){
                    emailEditText.setText("tappan@test.com");
                    passwordEditText.setText("Test12");
                }

                HashMap <String, String> params = new HashMap<String, String>();
                params.put("email", emailEditText.getText().toString());
                params.put("password", passwordEditText.getText().toString());
                //?username=testuser&password=password
                DataUtil dataUtil = new DataUtil("POST","registerUser.php?email="+emailEditText.getText().toString()+
                        "&password=" + passwordEditText.getText().toString());

                String jsonString = dataUtil.process(null);
                //Log.d(TAG, jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);

                String errorOccurred = null;
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    userID = jsonObj.getString("userid");

                }
                return jsonArray;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }


    }

    public class FrankLoginUser extends AsyncTask {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                //DataUtil dataUtil = new DataUtil("courseTrial.php");

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("email", emailEditText.getText().toString());
                params.put("password", passwordEditText.getText().toString());
                //?username=testuser&password=password
                DataUtil dataUtil = new DataUtil("GET", "loginTest.php?email=" + emailEditText.getText().toString() +
                        "&password=" + passwordEditText.getText().toString());

                String jsonString = dataUtil.process(null);
                //Log.d(TAG, jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);

                String errorOccurred = null;
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    userID = jsonObj.getString("UserID");

                    Intent intent = new Intent(Authentication.this, Courses.class);
                    //putArraysInIntent(intent);
                    intent.putExtra("userID", userID);
                    startActivity(intent);
                }

                return jsonArray;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }


    }

}
