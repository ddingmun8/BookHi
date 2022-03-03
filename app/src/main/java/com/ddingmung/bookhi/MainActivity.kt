package com.ddingmung.bookhi

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import com.ddingmung.bookhi.fragment.MainFragment
import com.ddingmung.bookhi.fragment.MyPageFragment
import com.ddingmung.bookhi.fragment.RegBookFragment
import java.security.MessageDigest

class MainActivity : AppCompatActivity(){
    lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //시작하면 mainFragment Show
        /*supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, ViewPagerFragment())
            .commit()*/

        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                var md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val something = String(Base64.encode(md.digest(), 0))
                Log.e("Hash key", something)
            }

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
        } catch (e: Exception) {
            Log.d("hashcode", "에러::" + e.toString())

        }
    }
}

