package com.example.firebase_kashy_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PatientManagementActivity extends AppCompatActivity {

    private Button btnAddPatient, btnViewPatients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_management);

        // Initialize buttons
        btnAddPatient = findViewById(R.id.btnpAddPatient);
        btnViewPatients = findViewById(R.id.btnpViewPatients);

        // Set click listeners
        btnAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Add Patient Activity
                Intent intent = new Intent(PatientManagementActivity.this, AddPatientActivity.class);
                startActivity(intent);
            }
        });

        btnViewPatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to View Patients Activity
                Intent intent = new Intent(PatientManagementActivity.this, ViewPatientsActivity.class);
                startActivity(intent);
            }
        });
    }
}
