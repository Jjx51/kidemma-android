package com.kidemma.home_admin.tabs.others.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.components.KidemmaCard
import com.kidemma.common.extensions.KidemmaSpacer
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.common.ui.theme.KidemmaTypography

@Composable
fun KidemmaOptionCard(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes title: Int,
    isVisible: Boolean = true,
    onClick: () -> Unit
) {
    if (isVisible.not()) return
    KidemmaCard(modifier) {
        Row(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            KidemmaSpacer(14.dp)
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(title),
                style = KidemmaTypography.bodyLarge.copy(color = KidemmaColors.Text)
            )
            KidemmaSpacer(14.dp)
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(30.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun KidemmaOptionCardPreview() {
    KidemmaTheme {
        KidemmaOptionCard(
            icon = R.drawable.ic_who_we_are,
            title = R.string.others_about_us
        ) {}
    }
}
