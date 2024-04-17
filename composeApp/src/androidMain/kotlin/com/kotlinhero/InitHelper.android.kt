package com.kotlinhero

import android.content.Context
import app.di.appModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun init(applicationContext: Context){
    Napier.base(DebugAntilog())
    startKoin {
        androidLogger()
        androidContext(applicationContext)
        modules(appModule())
    }
}