package com.example.firebase_kashy_app;


import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BarcodeScannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        findViewById(R.id.btnStartScan).setOnClickListener(v ->
                Toast.makeText(BarcodeScannerActivity.this, "Barcode scanning functionality coming soon!", Toast.LENGTH_SHORT).show());

    }
}
