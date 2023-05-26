package com.oss6team.emptylecturefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Global : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criteria_global)
        val global_town_btn = findViewById<View>(R.id.global_town_btn) as ImageButton
        global_town_btn.setOnClickListener {
            val intent = Intent(this@Global, Global_building::class.java)
            startActivity(intent)
        }

        //글로벌 시간표로 이동
        val global_time_btn = findViewById<View>(R.id.global_time_btn) as ImageButton
        global_time_btn.setOnClickListener {
            val campus=1;
            val intent = Intent(this@Global, CheckTime::class.java)
            startActivity(intent)
        }
    }
}