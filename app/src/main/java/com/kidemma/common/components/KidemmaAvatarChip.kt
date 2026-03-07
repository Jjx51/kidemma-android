package com.kidemma.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.ui.theme.KidemmaColors

@Composable
fun KidemmaAvatarChip(
    modifier: Modifier = Modifier,
    memberImage: Int
) {
    val avatar = painterResource(memberImage)

    Image(
        modifier = modifier
            .size(60.dp)
            .border(width = 3.dp, color = KidemmaColors.Icon, shape = CircleShape)
            .padding(6.dp),
        painter = avatar,
        contentDescription = stringResource(R.string.kidemma_avatar_chip_avatar),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun KidemmaAvatarChipPreview() {
    KidemmaAvatarChip(memberImage = R.drawable.img_boy)
}
