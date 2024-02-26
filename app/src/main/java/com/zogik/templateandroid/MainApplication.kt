package com.zogik.templateandroid

/**
 * Created by : Prayogi Sholihul
 * Mailto : prayogisholihul@gmail.com
 * Created at : Monday 26/02/2024: 14:26
 **/
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
