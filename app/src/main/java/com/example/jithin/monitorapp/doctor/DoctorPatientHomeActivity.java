package com.example.jithin.monitorapp.doctor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jithin.monitorapp.R;
import com.example.jithin.monitorapp.model.Patient;
import com.example.jithin.monitorapp.services.DoctorServiceImpl;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class DoctorPatientHomeActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private static final String TAG = "DoctorPatientHomeActivi";
    private Context mContext;

    //iniitailize database
    // TODO: 3/19/2018 firebase do later
   // FirebaseDatabase database;
    DatabaseReference reference;
    PieChart pieChart;
    ArrayList<Entry> userValues;
    ArrayList<String> xvalues;
    /*initialize widgets*/
    private TextView pNameTv, pAgeTv, pRiskScore, pSmoker, pTreating;

    private DoctorServiceImpl doctorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_patient_home);

        mContext = DoctorPatientHomeActivity.this;
        doctorService = new DoctorServiceImpl(this);

        initializeWidges();





    }

    /**
     * get patient data from server and update
     */
    private void setUpPatientData() {
        final String username = getIntent().getStringExtra("patientName");


        ParseQuery<ParseObject> patientDetails = ParseQuery.getQuery("UserDetails");

        patientDetails.whereEqualTo("owner", username);
        Log.i(TAG, "getPatentData: " + username);

        patientDetails.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                Log.i(TAG, "done: enterred into ");
                if (e == null){
                    Patient patient=doctorService.getDetails(object);
                    Log.i(TAG, "inside doc patient: "+patient.toString());

                    setUserDetails(patient, username);
                    setGraph(patient, username);

                }else {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    /**
     * set up graph
     * @param patient
     * @param username
     */
    private void setGraph(Patient patient, String username) {

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(patient.getPatient_weight(), "Weight"));
        entries.add(new PieEntry(patient.getpatient_height(), "Height"));
        entries.add(new PieEntry(patient.getPatient_total_chol(), "Total Cholestrol"));
        entries.add(new PieEntry(patient.getPatient_hdl_chol(), "HDL"));
        entries.add(new PieEntry(patient.getPatient_symbp(), "Symbolic BP"));

        PieDataSet dataSet = new PieDataSet(entries, "demo");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        pieChart.setContentDescription(username + "details");
        pieChart.setCenterTextSize(16);


        pieChart.setData(data);
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();

        pieChart.setOnChartValueSelectedListener(this);
    }

    /**
     * set up user details
     * @param patient
     * @param username
     */
    private void setUserDetails(Patient patient, String username) {
        pNameTv.setText("Name : " + username);
        pAgeTv.setText("Age :"+patient.getPatient_age());
        /*pRiskScore.setText("Risk Score : "+String.valueOf(riskScore));*/
        pSmoker.setText("Smoker : " + patient.getPatient_smoker());
        pTreating.setText("Have treatment for Blood pressure : " + patient.getPatient_have_treatment());
    }

    /**
     * initialize widgets
     */
    private void initializeWidges() {
        pNameTv = findViewById(R.id.patientNameTv);
        pAgeTv = findViewById(R.id.patientAgeTv);
        pSmoker = findViewById(R.id.patientSmoker);
        pTreating = findViewById(R.id.patientTreatment);
        pRiskScore = findViewById(R.id.patientRiskScore);
        pieChart = findViewById(R.id.newchart);

        setUpPatientData();
    }

    /**
     * notification button
     * @param view
     */
    public void sendAlert(View view) {
    }

    /**
     * notification button
     * @param view
     */
    public void sendMessage(View view) {
    }

    /**
     * Called when a value has been selected inside the chart.
     *
     * @param e The selected Entry
     * @param h The corresponding highlight object that contains information
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    /**
     * Called when nothing has been selected or an "un-select" has been made.
     */
    @Override
    public void onNothingSelected() {

    }
}
