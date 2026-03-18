package com.kidemma.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.R
import com.kidemma.common.components.KidemmaScrollableTextContent
import com.kidemma.common.components.KidemmaTopAppBarBack
import com.kidemma.common.ui.theme.KidemmaColors

/*
 * File: PrivacyPoliciesScreen
 * Description: Pantalla de Políticas de Privacidad
 *
 * Created by: Javier Cuéllar
 * Created on: 13/03/26
 * Last modified: 13/03/26
 */

@Composable
fun PrivacyPoliciesScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            KidemmaTopAppBarBack(
                title = stringResource(id = R.string.privacy_policies_title),
                onBackClick = onBackClick
            )
        },
        containerColor = KidemmaColors.Background,
    ) {
            paddingValues ->
        KidemmaScrollableTextContent(
            bodyText = stringResource(R.string.testing_paragraph),
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyPoliciesScreenPreview() {
    PrivacyPoliciesScreen {  }
}