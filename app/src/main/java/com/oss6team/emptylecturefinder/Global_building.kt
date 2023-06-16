package com.oss6team.emptylecturefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class Global_building : AppCompatActivity() {
    private lateinit var mAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_building_global)

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val campus = "1"

        setOnClickListenerForButton(R.id.btn_global_town1, campus, "0")
        setOnClickListenerForButton(R.id.btn_global_town2, campus, "1")
        setOnClickListenerForButton(R.id.btn_global_town3, campus, "2")
        setOnClickListenerForButton(R.id.btn_global_town4, campus, "3")
        setOnClickListenerForButton(R.id.btn_global_town5, campus, "4")
        setOnClickListenerForButton(R.id.btn_global_town6, campus, "5")
    }

    private fun setOnClickListenerForButton(buttonId: Int, campus: String, building: String) {
        val button = findViewById<Button>(buttonId)
        button.setOnClickListener {
            val intent = Intent(this@Global_building, LectureFound::class.java)
            intent.putExtra("campus", campus)
            intent.putExtra("building", building)
            startActivity(intent)
        }
    }
}
