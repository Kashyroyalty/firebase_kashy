package com.example.firebase_kashy_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var editfullname: EditText
    lateinit var editemail: EditText
    lateinit var editpassword: EditText
    lateinit var texttologin: TextView
    lateinit var createbutton: Button

    //initialze firebase
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editfullname = findViewById(R.id.edtname)
        editemail = findViewById(R.id.edtemail)
        editpassword = findViewById(R.id.edtpassword)
        texttologin = findViewById(R.id.tvttologin)
        createbutton = findViewById(R.id.btncreate)

        //initialize firebase again
        auth = FirebaseAuth.getInstance()

        createbutton.setOnClickListener {
            var name = editfullname.text.toString().trim()
            var email = editemail.text.toString().trim()
            var password = editpassword.text.toString().trim()


            //validate inputs
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()  ) {
                Toast.makeText(this, "One of the fields is empty", Toast.LENGTH_SHORT).show()
            } else {

                //create a user in firebase
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {

                    if (it.isSuccessful) {
                        Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show()
                        var gotologin = Intent(this, LoginActivity::class.java)
                        startActivity(gotologin)
                        finish()
                    } else {
                        Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }
        texttologin.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            // Start the LoginPageActivity
            startActivity(intent)
            }

        }
    }
