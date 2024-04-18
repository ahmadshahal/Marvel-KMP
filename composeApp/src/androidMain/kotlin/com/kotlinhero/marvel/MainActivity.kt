package com.kotlinhero.marvel

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.ui.App

class MainActivity : ComponentActivity() {

    companion object {
        var context: Context? = null
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = this

        enableEdgeToEdge()

        setContent {
            App()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        context = null
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}