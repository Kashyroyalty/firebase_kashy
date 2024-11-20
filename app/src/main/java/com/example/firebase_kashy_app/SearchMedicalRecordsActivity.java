package com.example.firebase_kashy_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchMedicalRecordsActivity extends AppCompatActivity {

    private EditText etBarcode;
    private Button btnSearch;
    private TextView tvRecordDetails;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_medical_records);

        // Initialize views
        etBarcode = findViewById(R.id.edtsBarcode);
        btnSearch = findViewById(R.id.btnsSearch);
        tvRecordDetails = findViewById(R.id.tvsRecordDetails);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("Patients");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByBarcode();
            }
        });
    }

    private void searchByBarcode() {
        String barcode = etBarcode.getText().toString().trim();

        if (TextUtils.isEmpty(barcode)) {
            Toast.makeText(this, "Enter barcode", Toast.LENGTH_SHORT).show();
            return;
        }

        databaseReference.orderByChild("barcode").equalTo(barcode).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                for (DataSnapshot data : task.getResult().getChildren()) {
                    Patient patient = data.getValue(Patient.class);
                    String details = "Name: " + patient.getName() + "\nAge: " + patient.getAge() + "\nGender: " + patient.getGender() + "\nContact: " + patient.getContact();
                    tvRecordDetails.setText(details);
                }
            } else {
                tvRecordDetails.setText("Invalid barcode");
            }
        });
    }
}
