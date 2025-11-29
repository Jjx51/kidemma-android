package com.kidemma.introduction.presentation.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.common.components.KidemmaBodyLarge
import com.kidemma.common.components.KidemmaHeadlineLarge
import com.kidemma.common.ui.theme.KidemmaTheme

@Composable
fun OnboardingScreen(exampleArg: String = "") {
    Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        KidemmaHeadlineLarge("Onboarding")
        Spacer(modifier = Modifier.height(24.dp))
        KidemmaBodyLarge("Hello $exampleArg!")
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    KidemmaTheme {
        OnboardingScreen("Preview")
    }
}