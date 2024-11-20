package com.example.firebase_kashy_app;


import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MedicalRecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records);

        findViewById(R.id.btnSearchRecords).setOnClickListener(v ->
                Toast.makeText(MedicalRecordsActivity.this, "Search Records functionality coming soon!", Toast.LENGTH_SHORT).show());

    }
}
