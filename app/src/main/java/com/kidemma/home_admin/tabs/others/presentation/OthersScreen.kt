package com.kidemma.home_admin.tabs.others.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens.BodyLarge
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.common.ui.theme.KidemmaTypography
import com.kidemma.home_admin.tabs.others.presentation.components.KidemmaOptionCard

@Composable
fun OthersScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = KidemmaColors.Background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        KidemmaOptionCard(
            icon = R.drawable.ic_admin_dashboard,
            title = R.string.administration_panel,
            onClick = {}
        )
        OthersSectionTitle(text = stringResource(R.string.public_links))
        KidemmaOptionCard(
            icon = R.drawable.ic_who_we_are,
            title = R.string.others_about_us,
            onClick = {}
        )

        KidemmaOptionCard(
            icon = R.drawable.ic_staff,
            title = R.string.others_staff,
            onClick = {}
        )

        KidemmaOptionCard(
            icon = R.drawable.ic_contact,
            title = R.string.others_contact_and_location,
            onClick = {}
        )

        KidemmaOptionCard(
            icon = R.drawable.ic_alliances,
            title = R.string.others_alliances,
            onClick = {}
        )

        KidemmaOptionCard(
            icon = R.drawable.ice_feedback,
            title = R.string.others_complaints_and_suggestions,
            onClick = {}
        )
    }
}


@Composable
private fun OthersSectionTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = KidemmaTypography.labelLarge.copy(fontSize = BodyLarge),
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
private fun OthersScreenPreview() {
    KidemmaTheme {
        OthersScreen()
    }

}