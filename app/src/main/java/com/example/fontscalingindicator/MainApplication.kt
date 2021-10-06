package com.example.fontscalingindicator

import android.app.Application
import android.provider.Settings

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FontScale.value = Settings.System.getFloat(contentResolver, Settings.System.FONT_SCALE)
    }
}