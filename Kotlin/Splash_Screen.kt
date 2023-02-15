package com.example.groove

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.example.groove.databinding.ActivityAboutBinding
import com.example.groove.databinding.ActivitySplashScreenBinding

class Splash_Screen : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val mainText : TextView = findViewById(R.id.groove_music)
        val groove = AnimationUtils.loadAnimation(this, R.anim.splash_screen_animation)
        mainText.startAnimation(groove)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}