package com.ddingmung.bookhi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.navigation.NavController
import com.ddingmung.bookhi.fragment.MainFragment
import com.ddingmung.bookhi.fragment.MyPageFragment
import com.ddingmung.bookhi.fragment.RegBookFragment

class MainActivity : AppCompatActivity(){
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //시작하면 mainFragment Show
        /*supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, ViewPagerFragment())
            .commit()*/

        val btnShowMain : Button = findViewById(R.id.btnShowMain)
        val btnRegBook : Button = findViewById(R.id.btnRegBook)
        val btnShowMyPage : Button = findViewById(R.id.btnShowMyPage)

        btnShowMain.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, MainFragment())
                .commit()
        }
        btnRegBook.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, RegBookFragment())
                .commit()
        }
        btnShowMyPage.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, MyPageFragment())
                .commit()
        }
    }
}

