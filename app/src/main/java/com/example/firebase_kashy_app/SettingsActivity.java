package com.example.firebase_kashy_app;


import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.btnChangePassword).setOnClickListener(v ->
                Toast.makeText(SettingsActivity.this, "Change Password functionality coming soon!", Toast.LENGTH_SHORT).show());

    }
}
