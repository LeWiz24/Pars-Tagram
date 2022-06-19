package com.olivares.parstagram.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.olivares.parstagram.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "RegisterActivity";
    EditText etUsername;
    EditText etPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Create ParseUser
//                ParseUser user = new ParseUser();
//                // Set core properties
//                //String username = etUsername.getText().toString();
//                //String password = etPassword.getText().toString();
//                user.setUsername(etUsername.getText().toString());
//                user.setPassword(etPassword.getText().toString());
//                // Invoke signUpInBackground
//                user.signUpInBackground(new SignUpCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        if (e == null){
//                            // let user continue
//                        } else{
//                            // log some stuff?
//                        }
//                    }
//                });
//                Intent goBack = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(goBack);
//            }
//        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.success(RegisterActivity.this,"Congrats, you are registered", Toasty.LENGTH_SHORT, true).show();
                // Create the ParseUser
                ParseUser user = new ParseUser();
                // Set core properties
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                user.setUsername(etUsername.getText().toString());
                user.setPassword(etPassword.getText().toString());
                // Set custom properties
                //user.put("phone", "650-253-0000");
                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });
            }
        });
    }
}

