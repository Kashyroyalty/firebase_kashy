package com.example.firebase_kashy_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var carmake:EditText
    lateinit var carmodel:EditText
    lateinit var carprice:EditText
    lateinit var upload_photo:Button
    lateinit var upload_data:Button
    lateinit var view_uploads:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carmake = findViewById(R.id.edtmake)
        carmodel = findViewById(R.id.edtmodel)
        carprice= findViewById(R.id.edtprice)
        upload_photo = findViewById(R.id.btncarphoto)
        upload_data = findViewById(R.id.btncardata)
        view_uploads = findViewById(R.id.btnviewcars)

        var database = FirebaseDatabase.getInstance()
        var databaseref =  database.getReference("cars")



        upload_photo.setOnClickListener {



        }



        upload_data.setOnClickListener {
            var carmake = carmake.text.toString().trim()
            var carmodel = carmodel.text.toString().trim()
            var carprice = carprice.text.toString().trim()

            if(carmake.isEmpty()|| carmodel.isEmpty()|| carprice.isEmpty()){
                Toast.makeText(this, "Cannot submit an empty form", Toast.LENGTH_SHORT).show()
            }else{
                //saving ino to firebase db
                var usercar = Car(carmake,carmodel,carprice)
                var ref = FirebaseDatabase.getInstance().getReference().child("Cars")

                ref.setValue(usercar).addOnCompleteListener{

                    if (it.isSuccessful){
                        Toast.makeText(this,"Car Data Uploaded Successfully",
                            Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Failed tO Save Car Info",
                            Toast.LENGTH_LONG).show()
                    }

                }



            }



        }
        view_uploads.setOnClickListener {



        }





        }
    }
