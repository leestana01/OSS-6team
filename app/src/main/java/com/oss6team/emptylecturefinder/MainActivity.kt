package com.oss6team.emptylecturefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //다크모드 비활성화
        setContentView(R.layout.activity_main)
        val seoul_btn = findViewById<View>(R.id.seoul_btn) as Button
        seoul_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, Seoul::class.java)
            startActivity(intent)
        }
        val global_btn = findViewById<View>(R.id.global_btn) as Button
        global_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, Global::class.java)
            startActivity(intent)
        }



    }
}