package com.example.jithin.monitorapp.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.jithin.monitorapp.R;
import com.example.jithin.monitorapp.services.AuthServiceImpl;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    // initialize widgets
    private EditText regUsernameEt, regEmailEt, regPasswordEt;
    private String usertype;

    // helper classes
    private AuthServiceImpl authService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        authService = new AuthServiceImpl(this);

        initalizeWidgets();
    }

    /**
     * initialize widgets
     */
    private void initalizeWidgets() {

        regUsernameEt = findViewById(R.id.regUsernameEt);
        regEmailEt = findViewById(R.id.regEmailEt);
        regPasswordEt = findViewById(R.id.registPasswordEt);

    }

    /**
     * register user button
     * @param view
     */
    public void regUser(View view) {

        Log.i(TAG, "regUser: register button clicked");

        String username = regUsernameEt.getText().toString().trim();
        String email = regEmailEt.getText().toString().trim();
        String password = regPasswordEt.getText().toString().trim();


        Log.i(TAG, "regUser: " + usertype);
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {

            Toast.makeText(this, "please fill out all the fields", Toast.LENGTH_SHORT).show();
        } else {

            authService.createUser(username,email,password,usertype);

        }
    }

    /**
     * select user type
     * @param view
     */
    public void selecUser(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.patientRadio:
                if (checked) {
                    usertype = "patient";
                    Toast.makeText(this, ((RadioButton) view).getText().toString(), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.doctorRadio:
                if (checked) {
                    Toast.makeText(this, ((RadioButton) view).getText(), Toast.LENGTH_SHORT).show();
                    usertype = "doctor";
                }
                break;
        }

    }
}
