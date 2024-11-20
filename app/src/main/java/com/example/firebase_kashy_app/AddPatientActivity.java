package com.example.firebase_kashy_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class AddPatientActivity extends AppCompatActivity {

    private EditText etName, etAge, etGender, etContact;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        // Initialize UI elements
        etName = findViewById(R.id.edtaname);
        etAge = findViewById(R.id.edtaage);
        etGender = findViewById(R.id.edtagender);
        etContact = findViewById(R.id.edtacontact);
        Button btnSavePatient = findViewById(R.id.btnSavePatient);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Save patient record on button click
        btnSavePatient.setOnClickListener(v -> savePatientRecord());
    }

    private void savePatientRecord() {
        // Get input values
        String name = etName.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        String gender = etGender.getText().toString().trim();
        String contact = etContact.getText().toString().trim();

        // Validate input fields
        if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || contact.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return; // Stop further execution if validation fails
        }

        // Generate a unique barcode
        String barcode = UUID.randomUUID().toString();

        // Create a Patient object
        Patient patient = new Patient(name, age, gender, contact, barcode);

        try {
            // Save to local database and get the result
            boolean isAdded = databaseHelper.addPatient(patient);

            if (isAdded) {
                // Show success message
                Toast.makeText(this, "Patient added successfully!", Toast.LENGTH_SHORT).show();

                // Clear fields after adding
                etName.setText("");
                etAge.setText("");
                etGender.setText("");
                etContact.setText("");
            } else {
                // Show failure message
                Toast.makeText(this, "Failed to add patient. Please try again.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Handle any unexpected errors
            Toast.makeText(this, "An error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
