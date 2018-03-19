package com.example.jithin.monitorapp.patient;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jithin.monitorapp.R;
import com.example.jithin.monitorapp.services.PatientServiceImpl;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class SurveyActivity extends AppCompatActivity {

    private static final String TAG = "SurveyActivity";
    String headPoint, nauseaPoint, fatiguePoint, faintingPoint, dizzPoint, pressurePoint, spinnerData;
    ArrayAdapter<CharSequence> adapter, headAdapter, nauseAdapter, faintAdapter, fatigueAdapter, pressureAdapter, dizziAdapter;

    /*initialize widgets*/
    private Spinner spinner, headSpinner, nauseaSpinner, fatiugeSpinner, fainSpinner, dizzinSpinner, pressureSpinner;

    private PatientServiceImpl patientService;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        mContext = SurveyActivity.this;
        patientService = new PatientServiceImpl(this);

        initalizeWidgets();
    }

    /**
     * initialize widgets
     */
    private void initalizeWidgets() {
        spinner = findViewById(R.id.acivityspinner);
        headSpinner = findViewById(R.id.headSpinner);
        nauseaSpinner = findViewById(R.id.nauseSpinner);
        fainSpinner = findViewById(R.id.faintSpinner);
        fatiugeSpinner = findViewById(R.id.fatigueSpinner);
        pressureSpinner = findViewById(R.id.pressureSpinner);
        dizzinSpinner = findViewById(R.id.dizzySpinner);


        setupActivitySpinner(spinner);
        setupHeadSpinner(headSpinner);
        setupNauseaSpinner(nauseaSpinner);
        setupFaintSpinner(fainSpinner);
        setupFatigueSpinner(fatiugeSpinner);
        setupDizzySpinner(dizzinSpinner);
        setupPressureSpinner(pressureSpinner);

    }

    //check pressure
    private void setupPressureSpinner(Spinner pressureSpinner) {


        pressureAdapter = ArrayAdapter.createFromResource(this, R.array.YesOrNo, android.R.layout.simple_spinner_dropdown_item);

        pressureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pressureSpinner.setAdapter(pressureAdapter);

        pressureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                pressurePoint = String.valueOf(parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    //check dizziness
    private void setupDizzySpinner(Spinner dizzinSpinner) {


        dizziAdapter = ArrayAdapter.createFromResource(this, R.array.YesOrNo, android.R.layout.simple_spinner_dropdown_item);

        dizziAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dizzinSpinner.setAdapter(dizziAdapter);

        dizzinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                dizzPoint = String.valueOf(parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    /*check Fatigue*/
    private void setupFatigueSpinner(Spinner fatiugeSpinner) {

        fatigueAdapter = ArrayAdapter.createFromResource(this, R.array.YesOrNo, android.R.layout.simple_spinner_dropdown_item);

        fatigueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fatiugeSpinner.setAdapter(fatigueAdapter);

        fatiugeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                fatiguePoint = String.valueOf(parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //check checkFaint
    private void setupFaintSpinner(Spinner fainSpinner) {


        faintAdapter = ArrayAdapter.createFromResource(this, R.array.YesOrNo, android.R.layout.simple_spinner_dropdown_item);

        faintAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fainSpinner.setAdapter(faintAdapter);

        fainSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                faintingPoint = String.valueOf(parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //chek nausea
    private void setupNauseaSpinner(Spinner nauseaSpinner) {

        nauseAdapter = ArrayAdapter.createFromResource(this, R.array.YesOrNo, android.R.layout.simple_spinner_dropdown_item);

        nauseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        nauseaSpinner.setAdapter(nauseAdapter);

        nauseaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                nauseaPoint = String.valueOf(parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //check lightheadness
    private void setupHeadSpinner(Spinner headSpinner) {


        headAdapter = ArrayAdapter.createFromResource(this, R.array.YesOrNo, android.R.layout.simple_spinner_dropdown_item);

        headAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        headSpinner.setAdapter(headAdapter);

        headSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                headPoint = String.valueOf(parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    //checking daily activity spinner
    private void setupActivitySpinner(Spinner spinner) {

        adapter = ArrayAdapter.createFromResource(this, R.array.activityspinner, android.R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SurveyActivity.this, "selected item is " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();

                spinnerData = String.valueOf(adapterView.getItemAtPosition(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    /**
     * upload clicked
     *
     * @param view
     */
    public void uploadData(View view) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Survey");
        query.whereEqualTo("owner", ParseUser.getCurrentUser().getUsername());

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {

                if (e == null) {

                    String objid = object.getString("objid");
                    Log.i(TAG, "getobjid: "+objid);

                    // upload data to server
                    patientService.uploadSurveyDataSecond(objid,headPoint,nauseaPoint
                            ,fatiguePoint,faintingPoint,dizzPoint,pressurePoint,spinnerData);

                } else {
                    Toast.makeText(SurveyActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}
