package com.kidemma.introduction.presentation.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import com.kidemma.R
import com.kidemma.common.components.KidemmaPrimaryButton
import com.kidemma.common.components.KidemmaHeadlineLarge
import com.kidemma.common.ui.theme.KidemmaTheme

@Composable
fun SplashScreen(navigateToOnboarding: (String) -> Unit = {}) {
    val context = LocalContext.current

    Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        KidemmaHeadlineLarge("Splash")
        Spacer(modifier = Modifier.height(24.dp))
        KidemmaPrimaryButton(text = "Go to Onboarding") {
            navigateToOnboarding(
                    getString(
                            context,
                            R.string.app_name
                    )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    KidemmaTheme {
        SplashScreen()
    }
}