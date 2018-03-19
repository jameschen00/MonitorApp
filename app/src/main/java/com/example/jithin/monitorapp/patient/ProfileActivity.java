package com.example.jithin.monitorapp.patient;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jithin.monitorapp.R;
import com.example.jithin.monitorapp.services.ParseHelperServicesImpl;
import com.example.jithin.monitorapp.services.PatientServiceImpl;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    // initialize widgets
    private EditText ageEt, weightEt, hieghtEt, totolcholestrolEt, hdlCholestroleEt, synBpEt;
    private Spinner smokerSpinner, treatmentSpinner;
    private ArrayAdapter<CharSequence> treatAdapter;
    private String havetreatment;
    private ArrayAdapter<CharSequence> smokerAdapter;
    private String havesmoker;

    private Context mContext;

    private PatientServiceImpl patientService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        patientService = new PatientServiceImpl(this);

        mContext = ProfileActivity.this;
        initializeWidgets();
    }
    /**
     * initialize widgets
     */
    private void initializeWidgets() {
        ageEt = findViewById(R.id.profile_age);
        weightEt = findViewById(R.id.profileweight);
        hieghtEt = findViewById(R.id.profileHeight);
        totolcholestrolEt = findViewById(R.id.totalChol);
        hdlCholestroleEt = findViewById(R.id.hdlChol);
        synBpEt = findViewById(R.id.sybpEt);

        smokerSpinner = findViewById(R.id.smokerSpinner);
        treatmentSpinner = findViewById(R.id.treatmentSpinner);

        setupSmokerSpinner(smokerSpinner);
        setupTreatmentSpinner(treatmentSpinner);
    }

    // spinners
    private void setupTreatmentSpinner(Spinner treatmentSpinner) {

        treatAdapter = ArrayAdapter.createFromResource(this, R.array.YesOrNo, android.R.layout.simple_dropdown_item_1line);
        treatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        treatmentSpinner.setAdapter(treatAdapter);

        treatmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                havetreatment = String.valueOf(parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(ProfileActivity.this, "Please Select anything...", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setupSmokerSpinner(Spinner smokerSpinner) {

        smokerAdapter = ArrayAdapter.createFromResource(this, R.array.YesOrNo, android.R.layout.simple_dropdown_item_1line);
        smokerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        smokerSpinner.setAdapter(smokerAdapter);

        smokerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                havesmoker = String.valueOf(parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(ProfileActivity.this, "Please select anything", Toast.LENGTH_SHORT).show();

            }
        });


    }

    /**
     * update button
     * @param view
     */
    public void updateUserDetail(View view) {
        final String age = ageEt.getText().toString().trim();
        final String weight = weightEt.getText().toString().trim();
        final String height = hieghtEt.getText().toString().trim();
        final String totalchol = totolcholestrolEt.getText().toString().trim();
        final String hdlChol = hdlCholestroleEt.getText().toString().trim();
        final String symbp = synBpEt.getText().toString().trim();

        if (age.isEmpty() || weight.isEmpty() || height.isEmpty() || totalchol.isEmpty() || hdlChol.isEmpty() || symbp.isEmpty()
                ) {

            Toast.makeText(this, "please fill all the fields", Toast.LENGTH_SHORT).show();

        } else {


            ///getting objid

            final ParseQuery<ParseObject> getobjQuery = ParseQuery.getQuery(mContext.getString(R.string.db_userDetails));
            getobjQuery.whereEqualTo("owner", ParseUser.getCurrentUser().getUsername());

            getobjQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {

                    if (e == null) {

                        String objid = object.getString("objid");

                        // calling services
                        patientService.updatingUserProfileDetails(objid,
                                age, weight, height, totalchol, hdlChol, symbp, havesmoker, havetreatment);


                    } else {

                        Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }
            });


            ageEt.getText().clear();
            weightEt.getText().clear();
            hieghtEt.getText().clear();
            totolcholestrolEt.getText().clear();
            hdlCholestroleEt.getText().clear();
            synBpEt.getText().clear();

        }
    }
}
