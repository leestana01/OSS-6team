package com.oss6team.emptylecturefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class CheckTime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_time)
// 각각 스위치, 버튼, 체크박스 9개 까지의 클릭을 변수로 잡아둠

        val and_or=findViewById<View>(R.id.and_or_swith)
        val submit = findViewById<View>(R.id.set_button)

        val class1_checkBox = findViewById<View>(R.id.checkbox_1st_class) as CheckBox
        val class2_checkBox = findViewById<View>(R.id.checkbox_2nd_class) as CheckBox
        val class3_checkBox = findViewById<View>(R.id.checkbox_3rd_class) as CheckBox
        val class4_checkBox = findViewById<View>(R.id.checkbox_4th_class) as CheckBox
        val class5_checkBox = findViewById<View>(R.id.checkbox_5th_class) as CheckBox
        val class6_checkBox = findViewById<View>(R.id.checkbox_6th_class) as CheckBox
        val class7_checkBox = findViewById<View>(R.id.checkbox_7th_class) as CheckBox
        val class8_checkBox = findViewById<View>(R.id.checkbox_8th_class) as CheckBox
        val class9_checkBox = findViewById<View>(R.id.checkbox_9th_class) as CheckBox


        if (class1_checkBox.isChecked){

        }

        submit.setOnClickListener {
            val intent = Intent(this@CheckTime, LectureFound::class.java)
            startActivity(intent)
        }
    }
}