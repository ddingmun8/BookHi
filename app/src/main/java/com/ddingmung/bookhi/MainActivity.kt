package com.ddingmung.bookhi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //시작하면 mainFragment Show
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragment, ViewPagerFragment())
            .commit()

        val btnShowMain : Button = findViewById(R.id.btnShowMain)
        val btnRegBook : Button = findViewById(R.id.btnRegBook)
        val btnShowMyPage : Button = findViewById(R.id.btnShowMyPage)

        btnShowMain.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragment, MainFragment())
                .commit()
        }
        btnRegBook.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragment, RegBookFragment())
                .commit()
        }
        btnShowMyPage.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragment, MyPageFragment())
                .commit()
        }

    }
}