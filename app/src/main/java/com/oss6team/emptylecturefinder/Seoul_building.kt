package com.oss6team.emptylecturefinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class Seoul_building : AppCompatActivity() {
    private lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_building)

        // 애드몹 광고 불러오기
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val campus = 0

        // 각 건물에 대한 버튼의 클릭 리스너 설정
        setOnClickListenerForButton(R.id.btn_seoul_town1, campus, "0")
        setOnClickListenerForButton(R.id.btn_seoul_town2, campus, "1")
        setOnClickListenerForButton(R.id.btn_seoul_town3, campus, "2")
        setOnClickListenerForButton(R.id.btn_seoul_town4, campus, "3")
        setOnClickListenerForButton(R.id.btn_seoul_town5, campus, "5")
        setOnClickListenerForButton(R.id.btn_seoul_town6, campus, "C")
    }

    // 버튼에 대한 클릭 리스너를 설정하는 함수
    private fun setOnClickListenerForButton(buttonId: Int, campus: Int, building: String) {
        val button = findViewById<Button>(buttonId)
        button.setOnClickListener {
            // 'LectureFound' 액티비티로 이동. 여기서 'campus'와 'building' 파라미터를 전달
            val intent = Intent(this@Seoul_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
    }
}

