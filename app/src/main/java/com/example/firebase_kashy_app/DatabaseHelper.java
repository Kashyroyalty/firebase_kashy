package com.example.firebase_kashy_app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hospital.db";
    private static final int DATABASE_VERSION = 2;

    // Table name
    private static final String TABLE_PATIENTS = "patients";
    private static final String TABLE_MEDICAL_RECORDS = "medical_records";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_CONTACT = "contact";


    // Medical Records Table Columns
    private static final String COLUMN_RECORD_ID = "record_id";
    private static final String COLUMN_PATIENT_ID = "patient_id";
    private static final String COLUMN_MEDICAL_HISTORY = "medical_history";
    private static final String COLUMN_VITAL_SIGNS = "vital_signs";
    private static final String COLUMN_EXAMINATION_NOTES = "examination_notes";
    private static final String COLUMN_TEST_RESULTS = "test_results";
    private static final String COLUMN_MEDICATIONS = "medications";
    private static final String COLUMN_SURGICAL_HISTORY = "surgical_history";
    private static final String COLUMN_REFERRALS = "referrals";
    private static final String COLUMN_CONSENT = "consent";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PATIENTS_TABLE = "CREATE TABLE " + TABLE_PATIENTS + "("
                + COLUMN_ID + " TEXT PRIMARY KEY," // id as TEXT (UUID)
                + COLUMN_NAME + " TEXT,"
                + COLUMN_AGE + " INTEGER,"
                + COLUMN_GENDER + " TEXT,"
                + COLUMN_CONTACT + " TEXT"
                + ")";
        db.execSQL(CREATE_PATIENTS_TABLE);

        // Create Medical Records Table
        String CREATE_MEDICAL_RECORDS_TABLE = "CREATE TABLE " + TABLE_MEDICAL_RECORDS + "("
                + COLUMN_RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PATIENT_ID + " TEXT,"
                + COLUMN_MEDICAL_HISTORY + " TEXT,"
                + COLUMN_VITAL_SIGNS + " TEXT,"
                + COLUMN_EXAMINATION_NOTES + " TEXT,"
                + COLUMN_TEST_RESULTS + " TEXT,"
                + COLUMN_MEDICATIONS + " TEXT,"
                + COLUMN_SURGICAL_HISTORY + " TEXT,"
                + COLUMN_REFERRALS + " TEXT,"
                + COLUMN_CONSENT + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_PATIENT_ID + ") REFERENCES " + TABLE_PATIENTS + "(" + COLUMN_ID + ")"
                + ")";
        db.execSQL(CREATE_MEDICAL_RECORDS_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICAL_RECORDS);
        onCreate(db);
    }

    // Method to add a medical record for a patient
    public boolean addMedicalRecord(String patientId, String medicalHistory, String vitalSigns, String examinationNotes, String testResults, String medications, String surgicalHistory, String referrals, String consent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT_ID, patientId);
        values.put(COLUMN_MEDICAL_HISTORY, medicalHistory);
        values.put(COLUMN_VITAL_SIGNS, vitalSigns);
        values.put(COLUMN_EXAMINATION_NOTES, examinationNotes);
        values.put(COLUMN_TEST_RESULTS, testResults);
        values.put(COLUMN_MEDICATIONS, medications);
        values.put(COLUMN_SURGICAL_HISTORY, surgicalHistory);
        values.put(COLUMN_REFERRALS, referrals);
        values.put(COLUMN_CONSENT, consent);

        long result = db.insert(TABLE_MEDICAL_RECORDS, null, values);
        db.close();

        return result != -1;
    }

    public List<MedicalRecord> getMedicalRecordsByPatientId(String patientId) {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get medical records based on patient ID
        String query = "SELECT * FROM " + TABLE_MEDICAL_RECORDS + " WHERE " + COLUMN_PATIENT_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{patientId});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") MedicalRecord record = new MedicalRecord(
                        cursor.getString(cursor.getColumnIndex(COLUMN_RECORD_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_MEDICAL_HISTORY)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_VITAL_SIGNS)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_EXAMINATION_NOTES)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TEST_RESULTS)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATIONS)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SURGICAL_HISTORY)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_REFERRALS)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CONSENT))
                );
                medicalRecords.add(record);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return medicalRecords;
    }

    public Patient getPatientById(String patientId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PATIENTS, null, COLUMN_ID + " = ?",
                new String[]{patientId}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") Patient patient = new Patient(
                    cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_AGE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT))
            );
            cursor.close();
            return patient;
        }
        db.close();
        return null;
    }



    // Add a new patient to the database
    public boolean addPatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, patient.getId()); // Save the unique ID
        values.put(COLUMN_NAME, patient.getName());
        values.put(COLUMN_AGE, patient.getAge());
        values.put(COLUMN_GENDER, patient.getGender());
        values.put(COLUMN_CONTACT, patient.getContact());

        // Perform insert operation
        long result = db.insert(TABLE_PATIENTS, null, values);
        db.close();

        // Check if insert was successful
        return result != -1;
    }



    // Get all patients from the database
    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PATIENTS, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") Patient patient = new Patient(
                            cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_AGE)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT))
                    );
                    patientList.add(patient);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return patientList;
    }

    public List<Patient> getPatientsByQuery(String query) {
        List<Patient> patientList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to search by name or ID
        String searchQuery = "SELECT * FROM " + TABLE_PATIENTS +
                " WHERE " + COLUMN_NAME + " LIKE ? OR " + COLUMN_ID + " LIKE ?";
        Cursor cursor = db.rawQuery(searchQuery, new String[]{"%" + query + "%", "%" + query + "%"});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") Patient patient = new Patient(
                            cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_AGE)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT))
                    );
                    patientList.add(patient);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return patientList;
    }



}
