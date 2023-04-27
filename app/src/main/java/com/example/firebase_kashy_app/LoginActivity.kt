package com.example.firebase_kashy_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.ims.ImsMmTelManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var mailedit:EditText
    lateinit var passwordedit:EditText
    lateinit var loginbutton:Button
    lateinit var registerbutton:Button

    lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mailedit =findViewById(R.id.edtemail)
        passwordedit = findViewById(R.id.edtpassword)
        loginbutton = findViewById(R.id.btnlogin)
        registerbutton = findViewById(R.id.edtregister)

        auth = FirebaseAuth.getInstance()


        loginbutton.setOnClickListener {

            var email = mailedit.text.toString().trim()
            var password = passwordedit.text.toString().trim()

            //validate inputs
            if (email.isEmpty()|| password.isEmpty()){
                Toast.makeText(this, "One of the inputs is empty", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show()

                        var gotomain = Intent(this, MainActivity::class.java)
                        startActivity(gotomain)
                        finish()
                    }else{
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        registerbutton.setOnClickListener {
            var gotoregister = Intent(this, RegisterActivity::class.java)
            startActivity(gotoregister)
            finish()
        }

    }
}