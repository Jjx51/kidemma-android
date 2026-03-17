package com.kidemma.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.common.components.KidemmaTopAppBarBack
import com.kidemma.R
import com.kidemma.common.components.KidemmaScrollableTextContent
import com.kidemma.common.ui.theme.KidemmaColors

/*
 * File: TermsAndConditionsScreen
 * Description: Pantalla de Términos y Condiciones
 *
 * Created by: Javier Cuéllar
 * Created on: 10/03/26
 * Last modified: 12/03/26
 */

@Composable
fun TermsAndConditionsScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            KidemmaTopAppBarBack(
                title = stringResource(id = R.string.terms_and_conditions_title),
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
fun TermsAndConditionsScreenPreview() {
    TermsAndConditionsScreen {  }
}
