package com.oss6team.emptylecturefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class CheckTime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_time)

        val campus = intent.getIntExtra("campus", 0)
        val andOr = findViewById<Switch>(R.id.and_or_swith)
        val submit = findViewById<View>(R.id.set_button)


        val checkboxesIds = listOf(
            R.id.checkbox_1st_class,
            R.id.checkbox_2nd_class,
            R.id.checkbox_3rd_class,
            R.id.checkbox_4th_class,
            R.id.checkbox_5th_class,
            R.id.checkbox_6th_class,
            R.id.checkbox_7th_class,
            R.id.checkbox_8th_class,
            R.id.checkbox_9th_class
        )

        val isCheckMap = mutableMapOf<Int, Int>()

        checkboxesIds.forEachIndexed { index, checkBoxId ->
            val checkBox = findViewById<View>(checkBoxId) as CheckBox
            isCheckMap[checkBoxId] = 0

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val message = if (isChecked) "${index+1}교시 체크됨" else "${index+1}교시 체크 해제됨"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                isCheckMap[checkBoxId] = if (isChecked) 1 else 0
            }
        }

        submit.setOnClickListener {
            val intent = Intent(this@CheckTime, LectureFound::class.java)
            intent.putIntegerArrayListExtra("lecture", ArrayList(isCheckMap.values.toList()))
            intent.putExtra("campus", campus)
            if (andOr.isChecked) {
                intent.putExtra("andOr", 1)
            } else {
                intent.putExtra("andOr", 0)
            }
            startActivity(intent)
        }
    }
}
