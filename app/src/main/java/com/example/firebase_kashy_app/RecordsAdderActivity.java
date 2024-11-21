package com.example.firebase_kashy_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RecordsAdderActivity extends AppCompatActivity {

    private EditText etSearchInput;

    private Button btnSearchRecords;
    private ListView lvPatientRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_adder);

        // Initialize UI elements
        etSearchInput = findViewById(R.id.etSearchInput);
        btnSearchRecords = findViewById(R.id.btnSearchRecords);
        lvPatientRecords = findViewById(R.id.lvPatientRecords);

        // Set up search button click listener
        btnSearchRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPatient();
            }
        });

        // Setup list view item click listener to handle patient selection
        lvPatientRecords.setOnItemClickListener((parent, view, position, id) -> {
            // Assuming that each record has patient ID and name, pass selected patient to the next activity
            String selectedPatientID = "123"; // This should be dynamically set based on list item data
            String selectedPatientName = "John Doe"; // This should also be set dynamically

            // Send the selected patient data to AddRecordsActivity
            Intent intent = new Intent(RecordsAdderActivity.this, AddRecordsActivity.class);
            intent.putExtra("patient_id", selectedPatientID);
            intent.putExtra("patient_name", selectedPatientName);
            startActivity(intent);
        });
    }

    private void searchPatient() {
        String searchQuery = etSearchInput.getText().toString().trim();

        // Perform validation
        if (searchQuery.isEmpty()) {
            Toast.makeText(this, "Please enter a name or ID to search", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a DatabaseHelper instance to interact with the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Get the list of patients matching the search query
        List<Patient> patients = dbHelper.getPatientsByQuery(searchQuery);

        // Check if no results are found
        if (patients.isEmpty()) {
            Toast.makeText(this, "No patients found for the given search criteria", Toast.LENGTH_SHORT).show();
        } else {
            // If results are found, update the ListView with the patient names
            List<String> patientNames = new ArrayList<>();
            for (Patient patient : patients) {
                patientNames.add(patient.getName());  // Assuming the Patient class has a getName() method
            }

            // Set the ListView adapter to display the patient names
            lvPatientRecords.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, patientNames));
        }
    }

}
