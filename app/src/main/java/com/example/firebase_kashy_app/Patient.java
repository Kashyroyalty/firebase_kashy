package com.example.firebase_kashy_app;

public class Patient {
    private String name;
    private String age;
    private String gender;
    private String contact;
    private String barcode;

    public Patient(String name, String age, String gender, String contact, String barcode) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getContact() {
        return contact;
    }

    public String getBarcode() {
        return barcode;
    }
}
