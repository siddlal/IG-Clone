package com.example.ig_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText edtEmail_loginActivity,edtPassword_loginActivity;
    public Button btnLogin_loginActivity,btnSignUp_loginActivity;
    public View layout_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        edtEmail_loginActivity = findViewById(R.id.edtEmail_loginActivity);
        edtPassword_loginActivity = findViewById(R.id.edtPassword_loginActivity);
        btnLogin_loginActivity = findViewById(R.id.btnLogin_loginActivity);
        btnSignUp_loginActivity = findViewById(R.id.btnSignup_loginActivity);
        layout_login = findViewById(R.id.layout_login);
        btnSignUp_loginActivity.setOnClickListener(this);
        btnLogin_loginActivity.setOnClickListener(this);
        layout_login.setOnClickListener(this);
        if(ParseUser.getCurrentUser() != null) {
           // ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin_loginActivity:

                if(edtEmail_loginActivity.getText().toString().equals("") || edtPassword_loginActivity.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this,"Email,username, password is required",Toast.LENGTH_SHORT).show();
                }
                else {

                    ParseUser.logInInBackground(edtEmail_loginActivity.getText().toString(), edtPassword_loginActivity.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                Toast.makeText(LoginActivity.this, user.getUsername() + " is Logged in Successfully", Toast.LENGTH_SHORT).show();
                                transitionToSocialMediaActivity();
                            } else
                                Toast.makeText(LoginActivity.this, "There is an error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


                break;
            case R.id.btnSignup_loginActivity:

                Intent intent =  new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
                break;

            case R.id.layout_login:
                try {

                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                } catch (Exception e) {

                    e.printStackTrace();

                }
                break;
        }
    }

    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
