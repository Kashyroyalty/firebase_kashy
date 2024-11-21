package com.example.firebase_kashy_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        Button btnPatientManagement = findViewById(R.id.btnPatientManagement);
        Button btnMedicalRecords = findViewById(R.id.btnMedicalRecords);
        Button btnBarcodeScanning = findViewById(R.id.btnBarcodeScanning);
        Button btnSettings = findViewById(R.id.btnSettings);

        // Welcome Message
        tvWelcome.setText("Welcome to Zambezi Hospital Dashboard");

        // Button Click Listeners
        btnPatientManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Patient Management Screen
                Intent intent = new Intent(DashboardActivity.this, PatientManagementActivity.class);
                startActivity(intent);
            }
        });

        btnMedicalRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Medical Records Screen
                Intent intent = new Intent(DashboardActivity.this, MedicalRecordsActivity.class);
                startActivity(intent);
            }
        });

        btnBarcodeScanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Barcode Scanning Screen
                Intent intent = new Intent(DashboardActivity.this, RecordsAdderActivity.class);
                startActivity(intent);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Settings Screen
                Intent intent = new Intent(DashboardActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }
}
