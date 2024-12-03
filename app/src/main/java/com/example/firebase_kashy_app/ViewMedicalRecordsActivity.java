/*package com.example.firebase_kashy_app;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ViewMedicalRecordsActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    // UI elements to display the records
    private TextView tvPatientId, tvPatientName, tvMedicalHistory, tvVitalSigns, tvExaminationNotes,
            tvTestResults, tvMedications, tvSurgicalHistory, tvReferrals, tvConsent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medical_records);

        // Initialize views
        tvPatientId = findViewById(R.id.tvPatientId);
        tvPatientName = findViewById(R.id.tvPatientName);
        tvMedicalHistory = findViewById(R.id.tvMedicalHistory);
        tvVitalSigns = findViewById(R.id.tvVitalSigns);
        tvExaminationNotes = findViewById(R.id.tvExaminationNotes);
        tvTestResults = findViewById(R.id.tvTestResults);
        tvMedications = findViewById(R.id.tvMedications);
        tvSurgicalHistory = findViewById(R.id.tvSurgicalHistory);
        tvReferrals = findViewById(R.id.tvReferrals);
        tvConsent = findViewById(R.id.tvConsent);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // Get the patient ID from the Intent
        String patientId = getIntent().getStringExtra("PATIENT_ID");

        if (patientId != null) {
            // Fetch and display the medical records for the selected patient
            displayPatientDetails(patientId);
        } else {
            Toast.makeText(this, "No patient selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayPatientDetails(String patientId) {
        // Fetch the patient and their medical records from the database
        List<MedicalRecord> records = databaseHelper.getMedicalRecordsByPatientId(patientId);

        if (records.isEmpty()) {
            Toast.makeText(this, "No medical records found", Toast.LENGTH_SHORT).show();
        } else {
            // Assuming the first record is the one we want to display (if you want to display multiple, you can handle it accordingly)
            MedicalRecord record = records.get(0);

            // Get the patient's name (assuming you have a method to get this)
            Patient patient = databaseHelper.getPatientById(patientId);

            // Display the patient's information
            if (patient != null) {
                tvPatientId.setText("Patient ID: " + patient.getId());
                tvPatientName.setText("Name: " + patient.getName());
            }

            // Display the medical records
            tvMedicalHistory.setText("Medical History: " + (record.getMedicalHistory() != null ? record.getMedicalHistory() : "N/A"));
            tvVitalSigns.setText("Vital Signs: " + (record.getVitalSigns() != null ? record.getVitalSigns() : "N/A"));
            tvExaminationNotes.setText("Examination Notes: " + (record.getExaminationNotes() != null ? record.getExaminationNotes() : "N/A"));
            tvTestResults.setText("Test Results: " + (record.getTestResults() != null ? record.getTestResults() : "N/A"));
            tvMedications.setText("Medications: " + (record.getMedications() != null ? record.getMedications() : "N/A"));
            tvSurgicalHistory.setText("Surgical History: " + (record.getSurgicalHistory() != null ? record.getSurgicalHistory() : "N/A"));
            tvReferrals.setText("Referrals: " + (record.getReferrals() != null ? record.getReferrals() : "N/A"));
            tvConsent.setText("Consent: " + (record.getConsent() != null ? record.getConsent() : "N/A"));
        }
    }
}*/

package com.example.firebase_kashy_app;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ViewMedicalRecordsActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    // UI elements to display the records
    private TextView tvPatientId, tvPatientName, tvMedicalHistory, tvVitalSigns, tvExaminationNotes,
            tvTestResults, tvMedications, tvSurgicalHistory, tvReferrals, tvConsent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medical_records);

        // Initialize views
        tvPatientId = findViewById(R.id.tvPatientId);
        tvPatientName = findViewById(R.id.tvPatientName);
        tvMedicalHistory = findViewById(R.id.tvMedicalHistory);
        tvVitalSigns = findViewById(R.id.tvVitalSigns);
        tvExaminationNotes = findViewById(R.id.tvExaminationNotes);
        tvTestResults = findViewById(R.id.tvTestResults);
        tvMedications = findViewById(R.id.tvMedications);
        tvSurgicalHistory = findViewById(R.id.tvSurgicalHistory);
        tvReferrals = findViewById(R.id.tvReferrals);
        tvConsent = findViewById(R.id.tvConsent);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // Get the patient ID from the Intent
        String patientId = getIntent().getStringExtra("PATIENT_ID");

        if (patientId != null) {
            // Fetch and display the medical records for the selected patient
            displayPatientDetails(patientId);
        } else {
            Toast.makeText(this, "No patient selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayPatientDetails(String patientId) {
        // Fetch the patient and their medical records from the database
        List<MedicalRecord> records = databaseHelper.getMedicalRecordsByPatientId(patientId);

        if (records.isEmpty()) {
            Toast.makeText(this, "No medical records found", Toast.LENGTH_SHORT).show();
        } else {
            // Assuming the first record is the one we want to display
            MedicalRecord record = records.get(0);

            // Get the patient's details
            Patient patient = databaseHelper.getPatientById(patientId);

            // Display the patient's information
            if (patient != null) {
                tvPatientId.setText("Patient ID: " + patient.getId());
                tvPatientName.setText("Name: " + patient.getName());
            }

            // Display the medical records with input data
            tvMedicalHistory.setText("Medical History: " + (record.getMedicalHistory() != null ? record.getMedicalHistory() : "N/A") + " (Input: " + record.getInputMedicalHistory() + ")");
            tvVitalSigns.setText("Vital Signs: " + (record.getVitalSigns() != null ? record.getVitalSigns() : "N/A") + " (Input: " + record.getInputVitalSigns() + ")");
            tvExaminationNotes.setText("Examination Notes: " + (record.getExaminationNotes() != null ? record.getExaminationNotes() : "N/A") + " (Input: " + record.getInputExaminationNotes() + ")");
            tvTestResults.setText("Test Results: " + (record.getTestResults() != null ? record.getTestResults() : "N/A") + " (Input: " + record.getInputTestResults() + ")");
            tvMedications.setText("Medications: " + (record.getMedications() != null ? record.getMedications() : "N/A") + " (Input: " + record.getInputMedications() + ")");
            tvSurgicalHistory.setText("Surgical History: " + (record.getSurgicalHistory() != null ? record.getSurgicalHistory() : "N/A") + " (Input: " + record.getInputSurgicalHistory() + ")");
            tvReferrals.setText("Referrals: " + (record.getReferrals() != null ? record.getReferrals() : "N/A") + " (Input: " + record.getInputReferrals() + ")");
            tvConsent.setText("Consent: " + (record.getConsent() != null ? record.getConsent() : "N/A") + " (Input: " + record.getInputConsent() + ")");
        }
    }
}