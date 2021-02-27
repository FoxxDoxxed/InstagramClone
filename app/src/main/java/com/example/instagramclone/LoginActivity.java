package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";

    //2c. added til loginuser because didnt exist
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //3e. after creating login activity, check if user is already logged in,
        // dont show login screen, instead show main activity
        if(ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

    }

    //created method since didnt exist
    public void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user " + username);
        //2a. there are two variations of parseuser: login & loginbackground. login
        //will execute on main thread whioch i dont want because user cant do
        //anything in app while request is completing. do it in background instead
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            //2b. override changes an existing method. parse will always pass in
            //a parse exception. if request succeeded e, will be null, otherwise
            //not null
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Issue with login", e);
                    return;
                }
                //TODO: Navigate to the main activity if the user has signed in properly
                goMainActivity();

                //toast to show suer they successfully logged in
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //this refers to activity and activity is an instance of a context
    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        //3c. after logging in, login activity should be removed from backstack with finish
        // otherwise, login screen will show when user presses back button
        //after login. so this will make the app close instead
        finish();
    }
}