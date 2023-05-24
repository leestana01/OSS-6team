package com.oss6team.emptylecturefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Global_building : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_building_global)

        val btn_global_town1 = findViewById<View>(R.id.btn_global_town1) as Button
        btn_global_town1.setOnClickListener {
            val campus=1;
            val building=11;

            val intent = Intent(this@Global_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
        val btn_global_town2 = findViewById<View>(R.id.btn_global_town2) as Button
        btn_global_town2.setOnClickListener {
            val campus=1;
            val building=12;

            val intent = Intent(this@Global_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
        val btn_global_town3 = findViewById<View>(R.id.btn_global_town3) as Button
        btn_global_town3.setOnClickListener {
            val campus=1;
            val building=13;

            val intent = Intent(this@Global_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
        val btn_global_town4 = findViewById<View>(R.id.btn_global_town4) as Button
        btn_global_town4.setOnClickListener {
            val campus=1;
            val building=14;

            val intent = Intent(this@Global_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
        val btn_global_town5 = findViewById<View>(R.id.btn_global_town5) as Button
        btn_global_town5.setOnClickListener {
            val campus=1;
            val building=15;

            val intent = Intent(this@Global_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
        val btn_global_town6 = findViewById<View>(R.id.btn_global_town6) as Button
        btn_global_town6.setOnClickListener {
            val campus=1;
            val building=16;

            val intent = Intent(this@Global_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
    }
}