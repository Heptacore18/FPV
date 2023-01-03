package com.example.fpv

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button_acces_gyroscope = findViewById<Button>(R.id.button_acces_gyroscope)
        val button_acces_video = findViewById<Button>(R.id.button_acces_video)

        button_acces_gyroscope.setOnClickListener{
        startActivity(Intent(applicationContext, DonneesGyroscope::class.java))
        }

        button_acces_video.setOnClickListener{
        startActivity(Intent(applicationContext, VideoActivity::class.java))
        }
    }
}

