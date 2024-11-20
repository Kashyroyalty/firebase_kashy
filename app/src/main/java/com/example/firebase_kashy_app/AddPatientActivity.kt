package com.example.firebase_kashy_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AddPatientActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var etGender: EditText
    private lateinit var etContact: EditText
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient)

        // Initialize UI elements
        etName = findViewById(R.id.edtaname)
        etAge = findViewById(R.id.edtaage)
        etGender = findViewById(R.id.edtagender)
        etContact = findViewById(R.id.edtacontact)
        val btnSavePatient: Button = findViewById(R.id.btnSavePatient)

        // Initialize DatabaseHelper
        databaseHelper = DatabaseHelper(this)

        // Save patient record on button click
        btnSavePatient.setOnClickListener { savePatientRecord() }
    }

    private fun savePatientRecord() {
        // Get input values
        val id = UUID.randomUUID().toString().trim() // Unique ID for the patient
        val name = etName.text.toString().trim()
        val age = etAge.text.toString().trim()
        val gender = etGender.text.toString().trim()
        val contact = etContact.text.toString().trim()

        // Validate input fields
        if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || contact.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return // Stop further execution if validation fails
        }

        // Create a Patient object
        val patient = Patient(id, name, age, gender, contact)

        try {
            // Save to local database and get the result
            val isAdded = databaseHelper.addPatient(patient)

            if (isAdded) {
                // Show success message
                Toast.makeText(this, "Patient added successfully!", Toast.LENGTH_SHORT).show()

                // Clear fields after adding
                etName.text.clear()
                etAge.text.clear()
                etGender.text.clear()
                etContact.text.clear()
            } else {
                // Show failure message
                Toast.makeText(this, "Failed to add patient. Please try again.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            // Handle any unexpected errors
            Toast.makeText(this, "An error occurred: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
}

