package com.kidemma.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kidemma.common.ui.theme.KidemmaColors

/*
 * File: KidemmaScrollableTextContent
 * Description: Composable que acepta texto y permite que sea scrollable
 *
 * Created by: Javier Cuéllar
 * Created on: 10/03/26
 * Last modified: 12/03/26
 */

@Composable
fun KidemmaScrollableTextContent(
    bodyText: String,
    modifier: Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(12.dp),
    ) {

        MainTextBody(bodyText = bodyText)

        VerticalSpacerExtraLarge()

    }
}

@Composable
private fun MainTextBody(
    bodyText: String,
    color: Color = KidemmaColors.Text
) {
    KidemmaLabelSmall(text = bodyText, color = color)
}