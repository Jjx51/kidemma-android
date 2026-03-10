package com.kidemma.home_admin.tabs.kids.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.components.KidDetailCard
import com.kidemma.common.components.KidemmaBodyLarge
import com.kidemma.common.components.KidemmaBodyMedium
import com.kidemma.common.components.VerticalSpacerSmall
import com.kidemma.common.domain.models.KidDetailCardUiModel
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.home_admin.tabs.kids.data.KidsTabMockProvider

/*
 * File: KidsTabBodyComponents.kt
 * Description: Kids tab body composables.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

@Composable
internal fun KidsListView(
    kidDetailList: List<KidDetailCardUiModel>,
    onKidClick: (KidDetailCardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            items = kidDetailList,
            key = { it.kidUiModel.id },
        ) { kidDetail ->
            KidDetailCard(
                kidDetailCardUiModel = kidDetail,
                onClick = { onKidClick(kidDetail) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
internal fun KidsGridView(
    kidDetailList: List<KidDetailCardUiModel>,
    onKidClick: (KidDetailCardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            items = kidDetailList,
            key = { it.kidUiModel.id },
        ) { kidDetail ->
            KidItem(
                kidDetail = kidDetail,
                onClick = { onKidClick(kidDetail) },
            )
        }
    }
}

@Composable
internal fun KidItem(
    kidDetail: KidDetailCardUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: Replace placeholder with kid avatar component once available.
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(KidemmaColors.DisabledButton),
        )
        VerticalSpacerSmall()
        KidemmaBodyLarge(
            modifier = Modifier
                .fillMaxWidth(),
            text = kidDetail.kidUiModel.name,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
internal fun EmptyKidsContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        KidemmaBodyMedium(text = stringResource(R.string.kids_empty_state_message))
    }
}

@Preview(showBackground = true)
@Composable
private fun KidsListViewPreview() {
    KidemmaTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(KidemmaColors.Background),
        ) {
            KidsListView(
                kidDetailList = KidsTabMockProvider.getKidsDetailList(),
                onKidClick = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun KidsGridViewPreview() {
    KidemmaTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(KidemmaColors.Background),
        ) {
            KidsGridView(
                kidDetailList = KidsTabMockProvider.getKidsDetailList(),
                onKidClick = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyKidsContentPreview() {
    KidemmaTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(KidemmaColors.Background),
        ) {
            EmptyKidsContent()
        }
    }
}
