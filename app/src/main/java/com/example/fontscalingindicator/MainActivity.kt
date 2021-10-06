package com.example.fontscalingindicator

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateView()
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    private fun updateView() {
        val tv = findViewById<TextView>(R.id.textScale)
        tv.text = "Current FontScale: " + FontScale.value.toString()
        val slider = findViewById<ProgressBar>(R.id.progressBar)
        slider.progress = (FontScale.value * 10).toInt()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val configScale = newConfig.fontScale
        Log.d("SAMBO", "onConfigurationChanged called, config font scale: "+configScale+", stored font scale: " + FontScale.value+", override: "+FontScale.override)
        //relaunch page so that new font scale is applied (capturing it here in onConfig change reads the value, but swallows the change)
        recreate()
    }

    // In case we want to override the font scale and see how it looks (need to restart activity to do this)
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        val config = Configuration(newBase?.resources?.configuration)
        val configScale = config.fontScale
        // override font scale
        if (FontScale.override) {
            config.fontScale = FontScale.value
            FontScale.override = false
        } else {
            FontScale.value = configScale
        }
        //config.fontScale = FontScale.value
        applyOverrideConfiguration(config)
        Log.d("SAMBO", "attachBaseContext called, config font scale: "+configScale+", stored font scale: " + FontScale.value+", override: "+FontScale.override)
    }
}