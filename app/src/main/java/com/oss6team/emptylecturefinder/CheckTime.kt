package com.oss6team.emptylecturefinder

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.professionalandrioid.apps.timeapp.R


class CheckTime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_time)

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
            Toast.makeText(this,"1교시 선택됨",Toast.LENGTH_LONG).show()
        }
    }
}