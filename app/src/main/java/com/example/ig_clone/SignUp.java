package com.example.ig_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    public Button btnLogin;
    public Button btnSignup;
    public EditText edtEmail,edtPassword,edtUsername;
    public View layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignUp);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        layout = (View) findViewById(R.id.layout);
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if(keyCode ==KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignup);
                }


                return false;
            }
        });

        edtUsername = findViewById(R.id.edtUsername);

        setTitle("SignUp");
        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        layout.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnSignUp:

                if(edtEmail.getText().toString().equals("") || edtUsername.getText().toString().equals("")||edtPassword.getText().toString().equals("")){
                    Toast.makeText(SignUp.this,"Email,username, password is required",Toast.LENGTH_SHORT).show();
                }
                else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up " + edtUsername.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(SignUp.this, appUser.getUsername() + " is signed up", Toast.LENGTH_SHORT).show();
                                transitionToSocialMediaActivity();
                            } else
                                Toast.makeText(SignUp.this, "There was an error" + e.getMessage(), Toast.LENGTH_SHORT).show();

                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.btnLogin:
                Intent intent =  new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);


                break;
            case R.id.layout:
                try {

                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                } catch (Exception e) {

                    e.printStackTrace();

                }
                break;



        }










//        R.id.layout;

//            try {
//
//                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//
//            }


    }
    private void transitionToSocialMediaActivity()  {
        Intent intent = new Intent(SignUp.this,SocialMediaActivity.class);
        startActivity(intent);

    }
}