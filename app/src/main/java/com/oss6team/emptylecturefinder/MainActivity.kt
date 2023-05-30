package com.oss6team.emptylecturefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //다크모드 비활성화

        setContentView(R.layout.activity_main)

        val seoul_btn = findViewById<View>(R.id.seoul_btn) as ImageButton
        seoul_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, Seoul::class.java)
            startActivity(intent)
        }
        val global_btn = findViewById<View>(R.id.global_btn) as ImageButton
        global_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, Global::class.java)
            startActivity(intent)
        }

        val favoriteButton = findViewById<Button>(R.id.favoriteButton)
        favoriteButton.setOnClickListener { // 즐겨찾기 액티비티로 이동하는 코드 작성
            val intent = Intent(this@MainActivity, Favorites::class.java)
            startActivity(intent)
        }


    }
}