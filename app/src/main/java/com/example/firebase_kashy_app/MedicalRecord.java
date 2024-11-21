package com.example.firebase_kashy_app;

public class MedicalRecord {
    private String recordId;
    private String patientId;
    private String medicalHistory;
    private String vitalSigns;
    private String examinationNotes;
    private String testResults;
    private String medications;
    private String surgicalHistory;
    private String referrals;
    private String consent;

    // Constructor
    public MedicalRecord(String recordId, String patientId, String medicalHistory, String vitalSigns,
                         String examinationNotes, String testResults, String medications,
                         String surgicalHistory, String referrals, String consent) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.medicalHistory = medicalHistory;
        this.vitalSigns = vitalSigns;
        this.examinationNotes = examinationNotes;
        this.testResults = testResults;
        this.medications = medications;
        this.surgicalHistory = surgicalHistory;
        this.referrals = referrals;
        this.consent = consent;
    }

    // Getters and Setters
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(String vitalSigns) {
        this.vitalSigns = vitalSigns;
    }

    public String getExaminationNotes() {
        return examinationNotes;
    }

    public void setExaminationNotes(String examinationNotes) {
        this.examinationNotes = examinationNotes;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getSurgicalHistory() {
        return surgicalHistory;
    }

    public void setSurgicalHistory(String surgicalHistory) {
        this.surgicalHistory = surgicalHistory;
    }

    public String getReferrals() {
        return referrals;
    }

    public void setReferrals(String referrals) {
        this.referrals = referrals;
    }

    public String getConsent() {
        return consent;
    }

    public void setConsent(String consent) {
        this.consent = consent;
    }
}
