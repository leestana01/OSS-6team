package com.oss6team.emptylecturefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class Seoul : AppCompatActivity() {
    private lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criteria)

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val seoul_town_btn = findViewById<View>(R.id.seoul_town_btn) as ImageButton
        seoul_town_btn.setOnClickListener {
            val intent = Intent(this@Seoul, Seoul_building::class.java)
            startActivity(intent)
        }

        //서울 시간표로 이동
        val seoul_time_btn = findViewById<View>(R.id.seoul_time_btn) as ImageButton
        seoul_time_btn.setOnClickListener {
            val intent = Intent(this@Seoul, CheckTime::class.java)
            intent.putExtra("campus", 0)
            startActivity(intent)
        }
    }
}