package com.oss6team.emptylecturefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Seoul_building : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_building)

        val btn_seoul_town1 = findViewById<View>(R.id.btn_seoul_town1) as Button
        btn_seoul_town1.setOnClickListener {
            val campus=0;
            val building=0;

            val intent = Intent(this@Seoul_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
        val btn_seoul_town2 = findViewById<View>(R.id.btn_seoul_town2) as Button
        btn_seoul_town2.setOnClickListener {
            val campus=0;
            val building=1;

            val intent = Intent(this@Seoul_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
        val btn_seoul_town3 = findViewById<View>(R.id.btn_seoul_town3) as Button
        btn_seoul_town3.setOnClickListener {
            val campus=0;
            val building=2;

            val intent = Intent(this@Seoul_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
        val btn_seoul_town4 = findViewById<View>(R.id.btn_seoul_town4) as Button
        btn_seoul_town4.setOnClickListener {
            val campus=0;
            val building=3;

            val intent = Intent(this@Seoul_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
        val btn_seoul_town5 = findViewById<View>(R.id.btn_seoul_town5) as Button
        btn_seoul_town5.setOnClickListener {
            val campus=0;
            val building=5;

            val intent = Intent(this@Seoul_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
        val btn_seoul_town6 = findViewById<View>(R.id.btn_seoul_town6) as Button
        btn_seoul_town6.setOnClickListener {
            val campus=0;
            val building="C";

            val intent = Intent(this@Seoul_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
    }
}