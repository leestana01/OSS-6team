package com.oss6team.emptylecturefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Seoul : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criteria)
        val seoul_town_btn = findViewById<View>(R.id.seoul_town_btn) as Button
        seoul_town_btn.setOnClickListener {
            val intent = Intent(this@Seoul, Seoul_building::class.java)
            startActivity(intent)
        }

        //서울 시간표로 이동
        val seoul_time_btn = findViewById<View>(R.id.seoul_time_btn) as Button
        seoul_time_btn.setOnClickListener {
            val intent = Intent(this@Seoul, CheckTime::class.java)
            startActivity(intent)
        }
    }
}