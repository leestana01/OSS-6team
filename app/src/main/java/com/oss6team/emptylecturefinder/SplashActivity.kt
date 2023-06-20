package com.oss6team.emptylecturefinder


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class SplashActivity : AppCompatActivity() {
    // 스플래시 이미지들을 저장하는 배열을 선언. 이 배열에는 각각의 스플래시 이미지 리소스 ID가 저장되어 있음
    private val splashImages = arrayOf(
        R.drawable.splash1,
        R.drawable.splash2,
        R.drawable.splash3,
        R.drawable.splash4,
        R.drawable.splash5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // 스플래시 화면의 레이아웃을 설정

        // 스플래시 이미지들 중에서 랜덤으로 하나를 선택하여 화면에 보임
        val randomImage = splashImages[Random.nextInt(0, splashImages.size)]
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(randomImage)

        // 2초 후에 메인 액티비티로 전환
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}
