package com.olivares.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import es.dmoral.toasty.Toasty;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    // Declaring fields
    EditText etUsername;
    EditText etPassword;
    Button btLogin;
    Button btRegister;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Using findview to connect fields to xml
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);

        // If user is logged in, go right to mainactivity
        if (ParseUser.getCurrentUser() != null){
            goToMainActivity();
        }
        // Set listener to go to camera activity

        // Set listener to login if user has an account
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Hello", "Please login");
                Toasty.success(LoginActivity.this, "Success!", Toast.LENGTH_SHORT, true).show();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

//        btRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String username = etUsername.getText().toString();
//                String password = etPassword.getText().toString();
//                Toasty.success(LoginActivity.this,"Congrats, you are registered", Toasty.LENGTH_SHORT, true).show();
//                // Create the ParseUser
//                ParseUser user = new ParseUser();
//                // Set core properties
//                user.setUsername(etUsername.getText().toString());
//                user.setPassword(etPassword.getText().toString());
//                // Set custom properties
//                //user.put("phone", "650-253-0000");
//                // Invoke signUpInBackground
//                user.signUpInBackground(new SignUpCallback() {
//                    public void done(ParseException e) {
//                        if (e == null) {
//                            // Hooray! Let them use the app now.
//                        } else {
//                            // Sign up didn't succeed. Look at the ParseException
//                            // to figure out what went wrong
//                        }
//                    }
//                });
//            }
//        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }


    private void loginUser(String username, String password) {
        Log.i("Hello","User is trying to login!");
        // TODO: Add login logic
        ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    // If no exception is found, use gotoactivity method
                    public void done(ParseUser user, ParseException e) {
                        if (e != null){
                            Toasty.error(LoginActivity.this, "Failed to authenticate", Toasty.LENGTH_SHORT, true).show();
                            Log.e(TAG,"Issue logging in",e);
                            return;
                        }
                        goToMainActivity();
                    }
                });
    }
    // Intent to navigate to main activity
    private void goToMainActivity() {
        Intent i = new Intent(this, FeedActivity.class);
        startActivity(i);
        finish();
    }
}