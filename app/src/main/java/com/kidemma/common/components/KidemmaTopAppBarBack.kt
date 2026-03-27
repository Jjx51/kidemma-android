package com.kidemma.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.R
import com.kidemma.common.ui.theme.KidemmaColors

/*
 * File: KidemmaTopAppBarBack
 * Description: Barra superior con backarrow para navegación hacia pantalla anterior
 *
 * Created by: Javier Cuéllar
 * Created on: 10/03/26
 * Last modified: 12/03/26
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KidemmaTopAppBarBack(
    title: String,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            KidemmaLabelLarge(text = title)
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.top_app_bar_back_arrow_content_description),
                    tint = KidemmaColors.Icon
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = KidemmaColors.Toolbar)
    )
}

@Preview(showBackground = true)
@Composable
fun KidemmaTopAppBarBackPreview() {
    KidemmaTopAppBarBack(
        title = stringResource(R.string.app_name)
    ) {

    }
}
