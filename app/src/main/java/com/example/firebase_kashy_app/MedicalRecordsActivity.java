package com.example.firebase_kashy_app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MedicalRecordsActivity extends AppCompatActivity {

    private EditText etSearchQuery;
    private ListView lvPatientRecords;
    private DatabaseHelper databaseHelper;
    private PatientAdapter patientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records);

        // Initialize UI elements
        etSearchQuery = findViewById(R.id.etSearchInput);
        lvPatientRecords = findViewById(R.id.lvPatientRecords);
        Button btnSearchRecords = findViewById(R.id.btnSearchRecords);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // Set onClickListener for search button
        btnSearchRecords.setOnClickListener(v -> {
            String query = etSearchQuery.getText().toString().trim();
            if (query.isEmpty()) {
                Toast.makeText(this, "Please enter a name or ID", Toast.LENGTH_SHORT).show();
            } else {
                searchRecords(query);
            }
        });

        // Handle item click in ListView
        lvPatientRecords.setOnItemClickListener((parent, view, position, id) -> {
            Patient selectedPatient = (Patient) parent.getItemAtPosition(position);
            Toast.makeText(MedicalRecordsActivity.this, "Selected: " + selectedPatient.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    private void searchRecords(String query) {
        List<Patient> matchingPatients = databaseHelper.getPatientsByQuery(query);
        if (matchingPatients.isEmpty()) {
            Toast.makeText(this, "No records found", Toast.LENGTH_SHORT).show();
        } else {
            displayRecords(matchingPatients);
        }
    }

    private void displayRecords(List<Patient> patients) {
        // Initialize the adapter and set it to the ListView
        patientAdapter = new PatientAdapter(this, patients);
        lvPatientRecords.setAdapter(patientAdapter);
    }
}
