package org.example.firstcmpproject

import android.app.Application
import org.example.firstcmpproject.di.initKoin
import org.koin.android.ext.koin.androidContext

class NetflixApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@NetflixApplication)
        }
    }
}