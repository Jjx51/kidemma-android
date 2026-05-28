package com.kidemma.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.common.components.KidemmaHorizontalSpacer
import com.kidemma.common.components.KidemmaSpacerSize
import com.kidemma.R
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens
import com.kidemma.common.ui.theme.KidemmaTheme

/*
 * File: KidemmaTextFieldWithFilter
 * Description: search bar and filter
 *
 * Created by: Laura Zermeño Pichardo
 * Created on: 27/02/26
 * Last modified: 12/03/26
 */
private val SearchBarHeight = 60.dp
@Composable
fun KidemmaTextFieldWithFilter(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClickFilter: () -> Unit = {}
) {


    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        KidemmaCard(
            modifier = Modifier
                .weight(1f)
                .height(SearchBarHeight)
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = value,
                    onValueChange = onValueChange,
                    placeholder = {
                        KidemmaLabelLarge(
                            text = stringResource(R.string.family_screen_write_something),
                            color = KidemmaColors.PlaceholderForm
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            tint = KidemmaColors.PlaceholderForm,
                            contentDescription = stringResource(R.string.texfield_with_filter_search_icon),
                            modifier = Modifier.size(KidemmaDimens.IconSizeMedium)
                        )
                    },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }

        KidemmaHorizontalSpacer(size = KidemmaSpacerSize.Small)

        KidemmaCard(
            modifier = Modifier
                .size(SearchBarHeight)
                .clickable { onClickFilter() }
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(R.drawable.ic_filter),
                    contentDescription = stringResource(R.string.texfield_with_filter_filter_icon),
                    tint = KidemmaColors.PlaceholderForm,
                    modifier = Modifier
                        .size(KidemmaDimens.IconSizeMedium)
                )
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun KidemmaTextFieldWithFilterPreview() {
    KidemmaTheme {
        var text by remember { mutableStateOf("") }
        KidemmaTextFieldWithFilter(
            value = text,
            onValueChange = { text = it },
            onClickFilter = {}
        )
    }
}