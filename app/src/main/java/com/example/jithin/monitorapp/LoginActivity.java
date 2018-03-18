package com.example.jithin.monitorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jithin.monitorapp.repository.AuthRepository;
import com.example.jithin.monitorapp.repository.AuthRepositoryImpl;
import com.example.jithin.monitorapp.services.AuthServiceImpl;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    //initialize widgets
    private TextView registerTv;
    private EditText logUsername, logPassword;

    private AuthServiceImpl authservices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        authservices = new AuthServiceImpl(this);

        intializeWidgets();
    }

    /**
     * initialize widgets
     */
    private void intializeWidgets() {

        registerTv = findViewById(R.id.registerTv);
        logUsername = findViewById(R.id.logUsernameET);
        logPassword = findViewById(R.id.logPasswordET);

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    /**
     * log button
     * @param view
     */
    public void log_user(View view) {


        Log.i(TAG, "regUser: button pressed");

        String userUsername = logUsername.getText().toString().trim();
        String userPassword = logPassword.getText().toString().trim();

        if (userUsername.isEmpty() || userPassword.isEmpty()) {

            Toast.makeText(this, "please fill out all the fields", Toast.LENGTH_SHORT).show();
        } else {

            authservices.loginUser(userUsername, userPassword);

        }

    }
}
