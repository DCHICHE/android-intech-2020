package com.example.intechcours

import android.R
import android.app.Application
import android.content.res.Configuration
import android.preference.PreferenceManager
import java.util.*


class App : Application() {

    private val locale: Locale? = null

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val language = sharedPreferences.getString("language", "bak")
    }


}