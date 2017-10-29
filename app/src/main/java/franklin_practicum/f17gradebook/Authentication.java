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

public class Authentication extends AppCompatActivity {

    private ArrayList<String> courses, assignments, courseGrades, assignmentGrades, assignmentDueDates;

    private void putArraysInIntent(Intent i){
        i.putExtra("courses", courses);
        i.putExtra("assignments", assignments);
        i.putExtra("courseGrades", courseGrades);
        i.putExtra("assignmentGrades", assignmentGrades);
        i.putExtra("assignmentDueDates", assignmentDueDates);
    }

    public ArrayList<String> getAssignments(){
        return assignments;
    }

    public void setAssignments(ArrayList<String> newList){
        assignments = newList;
    }

    private void getArraysFromIntent(){
        String[] coursesS = getIntent().getStringArrayExtra("courses");
        String[] assignmentsS = getIntent().getStringArrayExtra("assignments");
        String[] courseGradesS = getIntent().getStringArrayExtra("courseGrades");
        String[] assignmentGradesS = getIntent().getStringArrayExtra("assignmentGrades");
        String[] assignmentDueDatesS = getIntent().getStringArrayExtra("assignmentDueDates");
        for(String s: coursesS){courses.add(s);}
        for(String s: assignmentsS){assignments.add(s);}
        for(String s: courseGradesS){courseGrades.add(s);}
        for(String s: assignmentGradesS){assignmentGrades.add(s);}
        for(String s: assignmentDueDatesS){assignmentDueDates.add(s);}
    }

    private Button login, register;
    private ImageView header;
    private String screen;
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
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(validEmail && validPassword)
                Intent intent = new Intent(Authentication.this, Courses.class);
                //putArraysInIntent(intent);
                startActivity(intent);
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
                    Intent intent = new Intent(Authentication.this, Courses.class);
                    //putArraysInIntent(intent);
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
