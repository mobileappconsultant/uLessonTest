package com.arkangel.ulessontechnicaltest.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkangel.ulessontechnicaltest.android.features.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
