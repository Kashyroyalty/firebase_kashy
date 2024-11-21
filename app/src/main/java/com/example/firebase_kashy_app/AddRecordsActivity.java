package com.example.firebase_kashy_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddRecordsActivity extends AppCompatActivity {

    private EditText etMedicalHistory, etVitalSigns, etExaminationNotes, etTestResults, etMedications,
            etSurgicalHistory, etReferrals, etConsent;
    private String patientId;  // Pass the selected patient's ID from the previous screen
    private Button btnSaveRecords;  // Declare the button for saving the record
    private TextView tvPatientId, tvPatientName;  // TextViews for displaying patient's ID and name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_records);

        // Initialize EditText fields
        etMedicalHistory = findViewById(R.id.etMedicalHistory);
        etVitalSigns = findViewById(R.id.etBloodPressure);  // Assuming this field is for vital signs
        etExaminationNotes = findViewById(R.id.etExaminationNotes);
        etTestResults = findViewById(R.id.etTestResults);
        etMedications = findViewById(R.id.etCurrentMedications);
        etSurgicalHistory = findViewById(R.id.etSurgicalHistory);
        etReferrals = findViewById(R.id.etReferrals);
        etConsent = findViewById(R.id.etConsent);

        // Initialize TextViews for patient details
        tvPatientId = findViewById(R.id.tvPatientId);  // TextView to show the patient's ID
        tvPatientName = findViewById(R.id.tvPatientName);  // TextView to show the patient's name

        // Get the selected patient's ID (assumed passed via intent or from previous activity)
        patientId = getIntent().getStringExtra("patientId");  // Make sure the ID is passed from the previous activity

        // Initialize the Save button
        btnSaveRecords = findViewById(R.id.btnAddRecord);  // Make sure the ID matches the button in XML layout

        // Fetch and display the patient's ID and name based on the patientId
        if (patientId != null) {
            fetchPatientDetails(patientId);
        } else {
            Toast.makeText(this, "No patient selected", Toast.LENGTH_SHORT).show();
        }

        // OnClickListener to add record when the button is clicked
        btnSaveRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecord(v);  // Call the addRecord method when the button is clicked
            }
        });
    }

    // Fetch patient details based on patientId
    private void fetchPatientDetails(String patientId) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Patient patient = dbHelper.getPatientById(patientId);  // Assuming you have a method to get patient by ID

        if (patient != null) {
            // Display patient's ID and name
            tvPatientId.setText("Patient ID: " + patient.getId());
            tvPatientName.setText("Name: " + patient.getName());
        } else {
            Toast.makeText(this, "Patient not found", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to add record
    public void addRecord(View view) {
        // Get text entered by the user
        String medicalHistory = etMedicalHistory.getText().toString().trim();
        String vitalSigns = etVitalSigns.getText().toString().trim();
        String examinationNotes = etExaminationNotes.getText().toString().trim();
        String testResults = etTestResults.getText().toString().trim();
        String medications = etMedications.getText().toString().trim();
        String surgicalHistory = etSurgicalHistory.getText().toString().trim();
        String referrals = etReferrals.getText().toString().trim();
        String consent = etConsent.getText().toString().trim();

        // Ensure the fields are filled
        if (medicalHistory.isEmpty() || vitalSigns.isEmpty() || examinationNotes.isEmpty() || testResults.isEmpty() ||
                medications.isEmpty() || surgicalHistory.isEmpty() || referrals.isEmpty() || consent.isEmpty()) {
            Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add the medical record to the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        boolean isRecordAdded = dbHelper.addMedicalRecord(patientId, medicalHistory, vitalSigns, examinationNotes, testResults,
                medications, surgicalHistory, referrals, consent);

        if (isRecordAdded) {
            Toast.makeText(this, "Medical record added successfully", Toast.LENGTH_SHORT).show();

            // Clear all the EditText fields after successful record addition
            etMedicalHistory.setText("");
            etVitalSigns.setText("");
            etExaminationNotes.setText("");
            etTestResults.setText("");
            etMedications.setText("");
            etSurgicalHistory.setText("");
            etReferrals.setText("");
            etConsent.setText("");
        } else {
            Toast.makeText(this, "Failed to add medical record", Toast.LENGTH_SHORT).show();
        }
    }
}
