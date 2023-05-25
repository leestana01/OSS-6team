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
        var class1_ischeck : Int = 0;
// ischeck 변수는 체크 여부를 0 / 1 로 담아두었음.

        val class2_checkBox = findViewById<View>(R.id.checkbox_2nd_class) as CheckBox
        var class2_ischeck : Int = 0;

        val class3_checkBox = findViewById<View>(R.id.checkbox_3rd_class) as CheckBox
        var class3_ischeck : Int = 0;

        val class4_checkBox = findViewById<View>(R.id.checkbox_4th_class) as CheckBox
        var class4_ischeck : Int = 0;

        val class5_checkBox = findViewById<View>(R.id.checkbox_5th_class) as CheckBox
        var class5_ischeck : Int = 0;

        val class6_checkBox = findViewById<View>(R.id.checkbox_6th_class) as CheckBox
        var class6_ischeck : Int = 0;

        val class7_checkBox = findViewById<View>(R.id.checkbox_7th_class) as CheckBox
        var class7_ischeck : Int = 0;

        val class8_checkBox = findViewById<View>(R.id.checkbox_8th_class) as CheckBox
        var class8_ischeck : Int = 0;

        val class9_checkBox = findViewById<View>(R.id.checkbox_9th_class) as CheckBox
        var class9_ischeck : Int = 0;

// 체크박스가 체크되면 토스트메세지로 체크가시성 확보했음, 이후 변수에 1로 체크여부 저장.
        class1_checkBox.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "1교시 체크됨" else "1교시 체크 해제됨"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            class1_ischeck = if (isChecked) 1 else 0

        }
        // 위에것 숫자만 바꿈
        class2_checkBox.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "2교시 체크됨" else "2교시 체크 해제됨"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            class2_ischeck = if (isChecked) 1 else 0
        }
        class3_checkBox.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "3교시 체크됨" else "3교시 체크 해제됨"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            class3_ischeck = if (isChecked) 1 else 0
        }
        class4_checkBox.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "4교시 체크됨" else "4교시 체크 해제됨"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            class4_ischeck = if (isChecked) 1 else 0
        }
        class5_checkBox.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "5교시 체크됨" else "5교시 체크 해제됨"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            class5_ischeck = if (isChecked) 1 else 0
        }
        class6_checkBox.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "6교시 체크됨" else "6교시 체크 해제됨"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            class6_ischeck = if (isChecked) 1 else 0
        }
        class7_checkBox.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "7교시 체크됨" else "7교시 체크 해제됨"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            class7_ischeck = if (isChecked) 1 else 0
        }
        class8_checkBox.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "8교시 체크됨" else "8교시 체크 해제됨"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            class8_ischeck = if (isChecked) 1 else 0
        }
        class9_checkBox.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "9교시 체크됨" else "9교시 체크 해제됨"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            class9_ischeck = if (isChecked) 1 else 0
        }

//이후 제출버튼 클릭시 아래의 인텐트에 같이 int 데이터 같이 들어가도록 했음. 수정 필요할시 수정.
        submit.setOnClickListener {
            val intent = Intent(this@CheckTime, LectureFound::class.java)
            intent.putExtra("class1_ischeck", class1_ischeck)
            intent.putExtra("class2_ischeck", class2_ischeck)
            intent.putExtra("class3_ischeck", class3_ischeck)
            intent.putExtra("class4_ischeck", class4_ischeck)
            intent.putExtra("class5_ischeck", class5_ischeck)
            intent.putExtra("class6_ischeck", class6_ischeck)
            intent.putExtra("class7_ischeck", class7_ischeck)
            intent.putExtra("class8_ischeck", class8_ischeck)
            intent.putExtra("class9_ischeck", class9_ischeck)


            startActivity(intent)
        }
    }
}