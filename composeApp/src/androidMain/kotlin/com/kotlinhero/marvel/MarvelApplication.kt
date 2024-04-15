package com.kotlinhero.marvel

import android.app.Application
import com.kotlinhero.initKoin

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this@MarvelApplication)
    }
}