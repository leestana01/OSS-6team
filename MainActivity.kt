package com.example.hufs2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.radioButton)

        button.setOnClickListener {
            // 버튼을 클릭했을 때 동적으로 색상 변경
            button.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    androidx.constraintlayout.widget.R.color.abc_btn_colored_borderless_text_material
                )
            )
        }

        button2.setOnClickListener {
            // 버튼을 클릭했을 때 동적으로 색상 변경
            button2.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    androidx.constraintlayout.widget.R.color.abc_btn_colored_borderless_text_material
                )
            )
        }

        button4.setOnClickListener {
            // 버튼을 클릭했을 때 동적으로 색상 변경
            button4.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    androidx.constraintlayout.widget.R.color.abc_btn_colored_text_material
                )
            )
        }

        button3.setOnClickListener {
            // 버튼을 클릭했을 때 동적으로 색상 변경
            button3.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    androidx.constraintlayout.widget.R.color.abc_btn_colored_borderless_text_material
                )
            )
            when {
                button3.isPressed || button2.isPressed && button4.isPressed -> {
                    // Button3, Button2, 또는 Button4 중 하나라도 눌렸을 때 동작
                    navigateToNextActivity()
                }

                else -> {
                    // Button3, Button2, 또는 Button4 중 아무 버튼도 눌리지 않았을 때 메시지 출력 등의 처리
                    Toast.makeText(
                        this,
                        "Button3, Button2, 또는 Button4를 먼저 눌러주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    private fun navigateToNextActivity() {
        // 다음 화면으로 넘어가는 동작
        val intent = Intent(this, NextActivity::class.java)
        startActivity(intent)
    }
}