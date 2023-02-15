package com.example.groove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groove.databinding.ActivityAboutBinding
import com.example.groove.databinding.ActivitySettingsBinding

class AboutActivity : AppCompatActivity() {

    lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(MainActivity.currentThemeNav[MainActivity.themeIndex])
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}