package com.example.firebase_kashy_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PatientAdapter extends ArrayAdapter<Patient> {

    private Context context;
    private List<Patient> patients;

    public interface OnItemClickListener {
        void onItemClick(Patient patient);
    }

    public PatientAdapter(Context context, List<Patient> patients) {
        super(context, 0, patients);
        this.context = context;
        this.patients = patients;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.patient_item, parent, false);
        }

        Patient patient = patients.get(position);

        TextView tvId = convertView.findViewById(R.id.tViD);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvAge = convertView.findViewById(R.id.tvAge);
        TextView tvGender = convertView.findViewById(R.id.tvGender);
        TextView tvContact = convertView.findViewById(R.id.tvContact);

        tvId.setText(patient.getId());
        tvName.setText(patient.getName());
        tvAge.setText(patient.getAge());
        tvGender.setText(patient.getGender());
        tvContact.setText(patient.getContact());

        return convertView;
    }
}
