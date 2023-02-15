package com.example.groove

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AlertDialog
import com.example.groove.databinding.ActivitySettingsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SettingsActivity : AppCompatActivity() {

    lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(MainActivity.currentThemeNav[MainActivity.themeIndex])
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.backBtnSE.setOnClickListener { finish() }

        binding.MagentaTheme.setOnClickListener { saveTheme(0)}
        binding.OrangeTheme.setOnClickListener { saveTheme(1)}
        binding.PinkTheme.setOnClickListener { saveTheme(2)}
        binding.PurpleTheme.setOnClickListener { saveTheme(3)}
        binding.RedTheme.setOnClickListener { saveTheme(4)}
        binding.CyanTheme.setOnClickListener { saveTheme(5)}
        binding.LimeGreenTheme.setOnClickListener { saveTheme(6)}
        binding.MintGreenTheme.setOnClickListener { saveTheme(7)}
        binding.BlueTheme.setOnClickListener { saveTheme(8)}
        binding.YellowTheme.setOnClickListener { saveTheme(9)}
        binding.TomatoTheme.setOnClickListener { saveTheme(10)}
        binding.BabePinkTheme.setOnClickListener { saveTheme(11)}
        binding.PastelMagentaTheme.setOnClickListener { saveTheme(12)}
        binding.PastelPurpleTheme.setOnClickListener { saveTheme(13)}
        binding.PastelCyanTheme.setOnClickListener { saveTheme(14)}
        binding.WhiteTheme.setOnClickListener { saveTheme(15)}
        binding.AmoledPinkTheme.setOnClickListener { saveTheme(16)}
        binding.AmoledTheme.setOnClickListener { saveTheme(17)}
        binding.AmoledPurpleTheme.setOnClickListener { saveTheme(18)}
        binding.AmoledCyanTheme.setOnClickListener { saveTheme(19)}

     //   binding.versionName.text = setVersionDetails()
        binding.sortBtn.setOnClickListener {
            val menuList = arrayOf(Html.fromHtml("<font color='#C0C0C0'>Recently Added</font>"), Html.fromHtml("<font color='#C0C0C0'>Song Title</font>"), Html.fromHtml("<font color='#C0C0C0'>File Size</font>"))
            var currentSort = MainActivity.sortOrder
            val builder = MaterialAlertDialogBuilder(this, R.style.DefaultAlertDialogTheme)
            builder
                .setTitle(Html.fromHtml("<font color='#FFFFFF'>Sorting</font>"))
                .setPositiveButton("OK"){_, _ ->
                    val editor = getSharedPreferences("SORTING", MODE_PRIVATE).edit()
                    editor.putInt("sortOrder", currentSort)
                    editor.apply()
                }
                .setSingleChoiceItems(menuList, currentSort) {_, which ->
                    currentSort = which
                }
            val customDialog = builder.create()
            customDialog.show()
        //    customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
            customDialog.window?.setBackgroundDrawableResource(R.color.colorPrimary)
            setDialogBtnBackground(this, customDialog)

        }
    }

    private fun saveTheme(index: Int) {
        if (MainActivity.themeIndex != index)
        {
            val editor = getSharedPreferences("THEMES", MODE_PRIVATE).edit()
            editor.putInt("themeIndex", index)
            editor.apply()
            val builder = MaterialAlertDialogBuilder(this)
            builder
                .setTitle(Html.fromHtml("<font color='#FFFFFF'>Apply Theme</font>"))
                .setMessage(Html.fromHtml("<font color='#C0C0C0'>Do You Want to Apply this THEME..?</font>"))
                .setPositiveButton("YES"){_, _ ->
                    exitApplication()
                }
                .setNegativeButton("NO"){dialog, _ ->
                    dialog.dismiss()
                }
            val customDialog = builder.create()
            customDialog.show()
        //    customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
        //    customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED)
            customDialog.window?.setBackgroundDrawableResource(R.color.colorPrimary)
            setDialogBtnBackground(this, customDialog)
        }
    }
  /*  private fun setVersionDetails(): String {
        return "VERSION: ${BuildConfig.VERSION_NAME}"
    } */
}