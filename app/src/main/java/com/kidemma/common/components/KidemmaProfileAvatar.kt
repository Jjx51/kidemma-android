package com.kidemma.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kidemma.common.ui.models.ImageUiModel
import com.kidemma.common.ui.theme.KidemmaColors
import java.util.Locale

/*
 * File: KidemmaProfileAvatar
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 25/02/26
 */
@Composable
fun KidemmaProfileAvatar(
    name: String,
    image: ImageUiModel? = null,
    size: Dp = 40.dp,
    hasBorder: Boolean = true,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .then(
                if (hasBorder) {
                    Modifier.border(
                        width = 2.dp,
                        color = KidemmaColors.AvatarBorderColor,
                        shape = CircleShape
                    )
                } else {
                    Modifier
                }
            )
            .background(KidemmaColors.Background)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (image != null) {
            Image(
                painter = painterResource(id = image.resId),
                contentDescription = stringResource(id = image.contentDescription),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            val initial = name.firstOrNull()?.toString()?.uppercase(Locale.ROOT) ?: "?"
            Text(
                text = initial,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}
