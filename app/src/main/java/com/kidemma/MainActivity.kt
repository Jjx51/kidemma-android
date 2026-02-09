package com.kidemma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kidemma.common.navigation.NavigationWrapper
import com.kidemma.common.ui.theme.KidemmaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KidemmaTheme {
                NavigationWrapper()
            }
        }
    }
}