package com.kotlinhero

import android.content.Context
import app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun initKoin(applicationContext: Context){
    startKoin {
        androidLogger()
        androidContext(applicationContext)
        modules(appModule())
    }
}