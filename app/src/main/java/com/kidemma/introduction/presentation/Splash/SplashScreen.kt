package com.kidemma.introduction.presentation.Splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import com.kidemma.R

@Composable
fun SplashScreen(navigateToOnboarding: (String) -> Unit = {}) {
    val context = LocalContext.current

    Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
                "Splash",
                fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            navigateToOnboarding(
                    getString(
                            context,
                            R.string.app_name
                    )
            )
        }) {
            Text(text = "Go to Onboarding")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}