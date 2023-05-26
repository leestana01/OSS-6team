package com.oss6team.emptylecturefinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Seoul_building : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_building)

        val campus = "0"

        setOnClickListenerForButton(R.id.btn_seoul_town1, campus, "0")
        setOnClickListenerForButton(R.id.btn_seoul_town2, campus, "1")
        setOnClickListenerForButton(R.id.btn_seoul_town3, campus, "2")
        setOnClickListenerForButton(R.id.btn_seoul_town4, campus, "3")
        setOnClickListenerForButton(R.id.btn_seoul_town5, campus, "5")
        setOnClickListenerForButton(R.id.btn_seoul_town6, campus, "C")
    }

    private fun setOnClickListenerForButton(buttonId: Int, campus: String, building: String) {
        val button = findViewById<Button>(buttonId)
        button.setOnClickListener {
            val intent = Intent(this@Seoul_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
    }
}
