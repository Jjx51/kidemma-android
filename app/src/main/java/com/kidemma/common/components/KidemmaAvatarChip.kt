package com.kidemma.common.components

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

/*
 * File: KidemmaAvatarChip
 * Description: avatar chip circular
 *
 * Created by: Laura Zermeño Pichardo
 * Created on: 27/02/26
 * Last modified: 12/03/26
 */

private val AvatarChipBorderWidth = 3.dp

@Composable
fun KidemmaAvatarChip(
    modifier: Modifier = Modifier,
    memberImage: Int,
    memberErrorImage: Int
) {

    AsyncImage(
        model = memberImage,
        contentDescription = stringResource(R.string.kidemma_avatar_chip_avatar),
        modifier = modifier
            .size(KidemmaDimens.AvatarSize)
            .clip(CircleShape)
            .border(width = AvatarChipBorderWidth, color = KidemmaColors.ImageBorderStrokeColor, shape = CircleShape),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(memberErrorImage),
        error = painterResource(memberErrorImage),
        fallback = painterResource(memberErrorImage)
    )
}

@Preview(showBackground = true)
@Composable
fun KidemmaAvatarChipPreview() {
    KidemmaAvatarChip(memberImage = R.drawable.img_mom, memberErrorImage = R.drawable.img_boy)
}
