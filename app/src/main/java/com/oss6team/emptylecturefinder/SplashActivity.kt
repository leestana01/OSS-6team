package com.oss6team.emptylecturefinder


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class SplashActivity : AppCompatActivity() {

    private val splashImages = arrayOf(
        R.drawable.splash1,
        R.drawable.splash2,
        R.drawable.splash3,
        R.drawable.splash4,
        R.drawable.splash5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 랜덤으로 스플래시 이미지 선택
        val randomImage = splashImages[Random.nextInt(0, splashImages.size)]

        // 스플래시 이미지를 표시하는 코드 (예를 들어, ImageView를 사용한다고 가정)
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(randomImage)

        // 일정 시간 후에 메인 액티비티로 전환
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // 2초 후에 메인 액티비티로 전환
    }
}
