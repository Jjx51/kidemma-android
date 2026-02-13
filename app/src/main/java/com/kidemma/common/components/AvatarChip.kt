package com.kidemma.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.ui.theme.KidemmaColors

@Composable
fun AvatarChip(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.size(60.dp),
        shape = CircleShape,
        border = BorderStroke(3.dp, color = KidemmaColors.Icon),
        colors = CardDefaults.cardColors(containerColor = KidemmaColors.Secondary)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.img_avatar),
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop
        )

    }
}

@Preview(showBackground = true)
@Composable
fun AvatarChipPreview(modifier: Modifier = Modifier) {
    AvatarChip()
}
