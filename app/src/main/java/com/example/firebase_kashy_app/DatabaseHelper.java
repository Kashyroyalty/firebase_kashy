package com.example.firebase_kashy_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hospital.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_PATIENTS = "patients";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_BARCODE = "barcode";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PATIENTS_TABLE = "CREATE TABLE " + TABLE_PATIENTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_AGE + " TEXT,"
                + COLUMN_GENDER + " TEXT,"
                + COLUMN_CONTACT + " TEXT,"
                + COLUMN_BARCODE + " TEXT"
                + ")";
        db.execSQL(CREATE_PATIENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
        onCreate(db);
    }

    // Add a new patient to the database
    public void addPatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, patient.getName());
        values.put(COLUMN_AGE, patient.getAge());
        values.put(COLUMN_GENDER, patient.getGender());
        values.put(COLUMN_CONTACT, patient.getContact());
        values.put(COLUMN_BARCODE, patient.getBarcode());

        db.insert(TABLE_PATIENTS, null, values);
        db.close();
    }

    // Get all patients from the database
    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PATIENTS, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Patient patient = new Patient(
                            cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_AGE)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_BARCODE))
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
