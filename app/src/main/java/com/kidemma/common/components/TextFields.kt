package com.kidemma.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.kidemma.R
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens
import com.kidemma.common.ui.theme.KidemmaTheme

@Composable
fun KidemmaTextFieldWithFilter(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder : String = stringResource(R.string.family_screen_write_something),
    onClickFilter: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row (verticalAlignment = Alignment.CenterVertically){
        Card(
            modifier = modifier
                .weight(4f)
                .height(60.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = KidemmaDimens.CardElevation
            )
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = value,
                    onValueChange = onValueChange,
                    placeholder = {
                        KidemmaLabelLarge(
                            text = placeholder,
                            color = KidemmaColors.PlaceholderForm
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            tint = KidemmaColors.PlaceholderForm,
                            contentDescription = "Search Icon",
                            modifier = Modifier.size(30.dp)
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

        Spacer(Modifier.width(10.dp))

        Column(Modifier.weight(1f)) {
            Card(
                modifier = Modifier.size(60.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = KidemmaColors.Secondary),
                elevation = CardDefaults.cardElevation(defaultElevation = KidemmaDimens.CardElevation)
            ) {
                Box (Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(R.drawable.ic_filter),
                        contentDescription = "Filter Icon",
                        tint = KidemmaColors.PlaceholderForm,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { onClickFilter() },
                    )
                }

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