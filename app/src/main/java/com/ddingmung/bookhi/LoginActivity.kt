package com.ddingmung.bookhi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin : Button = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            btnLoginClick()
        }
        
    }
    fun btnLoginClick(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}