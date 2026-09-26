package com.gmail.brandonvel04.android_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gmail.brandonvel04.android_compose.ui.theme.AndroidcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidcomposeTheme {
                AppNavigation()
            }
        }
    }
}