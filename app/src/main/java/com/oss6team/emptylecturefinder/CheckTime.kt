package com.oss6team.emptylecturefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView


class CheckTime : AppCompatActivity() {
    private lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_time)

        // 애드몹 광고 불러오기
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val campus = intent.getIntExtra("campus", 0)
        val andOr = findViewById<ToggleButton>(R.id.and_or_toggle)
        val submit = findViewById<View>(R.id.set_button)

        // 캠퍼스 이름 설정
        if (campus == 0) {
            findViewById<TextView>(R.id.campusName).setText(R.string.Campus_Seoul)
        }

        // 교시에 대한 체크박스 아이디들
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

        // 각 체크박스의 체크 상태를 저장하는 맵
        val isCheckMap = mutableMapOf<Int, Int>()

        // 각 체크박스에 대해 체크 변경 리스너 설정
        checkboxesIds.forEachIndexed { index, checkBoxId ->
            val checkBox = findViewById<View>(checkBoxId) as CheckBox
            isCheckMap[checkBoxId] = 0

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val message = if (isChecked) "${index+1}교시 체크됨" else "${index+1}교시 체크 해제됨"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                isCheckMap[checkBoxId] = if (isChecked) 1 else 0
            }
        }

        // '제출' 버튼 클릭 리스너 설정
        submit.setOnClickListener {
            // 'LectureFound' 액티비티로 이동. 여기서 교시 정보, 캠퍼스, andOr 정보를 전달
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
