package com.ddingmung.bookhi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        delaySplash()
    }

    private fun delaySplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, loginActivity::class.java))
            finish()
        }, 2000)
    }

}