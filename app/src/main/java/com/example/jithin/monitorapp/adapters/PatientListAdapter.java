package com.example.jithin.monitorapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jithin.monitorapp.R;

import com.example.jithin.monitorapp.model.Patientdetails;


import java.util.ArrayList;

/**
 * Created by Master on 1/22/2018.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.MyViewHolder> {

    private ArrayList<Patientdetails> patientList;
    private Context mContext;

    public PatientListAdapter() {
    }

    public PatientListAdapter(Context mContext, ArrayList<Patientdetails> patientList) {
        this.mContext = mContext;
        this.patientList = patientList;

    }


    @Override
    public PatientListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_list_form, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PatientListAdapter.MyViewHolder holder, int position) {


        holder.patientName.setText(patientList.get(position).getUsername());
        holder.patientStatus.setText(patientList.get(position).getStatus());
        final String patientUserName = patientList.get(position).getUsername();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Intent intent = new Intent(mContext, DoctorPatientHomeActivity.class);

                intent.putExtra("patientName", patientUserName);

                mContext.startActivity(intent);*/

                Toast.makeText(mContext, "pressed", Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView patientName, patientStatus;

        public MyViewHolder(View itemView) {
            super(itemView);

            patientName = itemView.findViewById(R.id.patientName);
            patientStatus = itemView.findViewById(R.id.patientStatus);


        }
    }
}
