package com.kidemma.common.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kidemma.R
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens
import com.kidemma.common.ui.theme.KidemmaTheme

/*
 * File: KidemmaAvatar
 * Description: circular avatar component
 *
 * Created by: Laura Zermeño Pichardo
 * Created on: 27/02/26
 * Last modified: 28/05/26
 */

@Composable
fun KidemmaAvatar(
    modifier: Modifier = Modifier.size(KidemmaDimens.AvatarSize),
    @DrawableRes memberImage: Int,
    @DrawableRes memberErrorImage: Int,
    contentDescription: String? = stringResource(R.string.kidemma_avatar)
) {
    AsyncImage(
        model = memberImage,
        contentDescription = contentDescription,
        modifier = modifier
            .clip(CircleShape)
            .border(
                width = KidemmaDimens.AvatarBorderWidth,
                color = KidemmaColors.ImageBorderStrokeColor,
                shape = CircleShape
            ),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(memberErrorImage),
        error = painterResource(memberErrorImage),
        fallback = painterResource(memberErrorImage)
    )
}

@Preview(showBackground = true)
@Composable
fun KidemmaAvatarPreview() {
    KidemmaTheme {
        KidemmaAvatar(
            memberImage = R.drawable.img_mom,
            memberErrorImage = R.drawable.img_boy,
            contentDescription = "Avatar"
        )
    }
}
