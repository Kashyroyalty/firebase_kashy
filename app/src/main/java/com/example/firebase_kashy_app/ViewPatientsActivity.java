package com.example.firebase_kashy_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ViewPatientsActivity extends AppCompatActivity {

    private EditText etAdminPassword;
    private EditText etSearch;
    private Button btnViewPatients;
    private Button btnSearch;
    private ListView lvPatients;
    private DatabaseHelper databaseHelper;

    // Set the admin password (you can change it as needed)
    private static final String ADMIN_PASSWORD = "380016";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patients_records);

        etAdminPassword = findViewById(R.id.edtAdminPassword);
        etSearch = findViewById(R.id.edtSearch); // New EditText for searching
        btnViewPatients = findViewById(R.id.btnvLogin);
        btnSearch = findViewById(R.id.btnSearch); // New button for search
        lvPatients = findViewById(R.id.lvvPatients);

        databaseHelper = new DatabaseHelper(this);

        btnViewPatients.setOnClickListener(v -> validateAdminPassword());
        btnSearch.setOnClickListener(v -> searchPatients());
    }

    private void validateAdminPassword() {
        String enteredPassword = etAdminPassword.getText().toString().trim();

        if (TextUtils.isEmpty(enteredPassword)) {
            Toast.makeText(this, "Please enter the admin password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (enteredPassword.equals(ADMIN_PASSWORD)) {
            // Fetch and display all patient records
            displayPatientRecords();
        } else {
            // Show error dialog if password is incorrect
            new AlertDialog.Builder(this)
                    .setMessage("Incorrect password. Please try again.")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    private void displayPatientRecords() {
        List<Patient> patientList = databaseHelper.getAllPatients();

        if (patientList.isEmpty()) {
            Toast.makeText(this, "No patient records available", Toast.LENGTH_SHORT).show();
        } else {
            // Create and set the adapter for the ListView
            PatientAdapter adapter = new PatientAdapter(this, patientList);
            lvPatients.setAdapter(adapter);
        }
    }

    private void searchPatients() {
        String searchQuery = etSearch.getText().toString().trim();

        if (TextUtils.isEmpty(searchQuery)) {
            Toast.makeText(this, "Please enter a name or unique number to search", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Patient> searchResults = databaseHelper.searchPatients(searchQuery);

        if (searchResults.isEmpty()) {
            Toast.makeText(this, "No matching patient records found", Toast.LENGTH_SHORT).show();
        } else {
            // Create and set the adapter for the ListView with search results
            PatientAdapter adapter = new PatientAdapter(this, searchResults);
            lvPatients.setAdapter(adapter);
        }
    }

}
