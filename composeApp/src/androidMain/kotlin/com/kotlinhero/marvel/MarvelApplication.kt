package com.kotlinhero.marvel

import android.app.Application
import com.kotlinhero.init

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        init()
    }
}