package com.kidemma.home_admin.tabs.families

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.authentication.domain.model.FamilyUiModel
import com.kidemma.common.components.KidemmaFamilyCardItem
import com.kidemma.common.components.KidemmaPrimaryButton
import com.kidemma.common.components.KidemmaTextFieldWithFilter
import com.kidemma.common.ui.theme.KidemmaColors

@Composable
fun CreateFamilyScreen() {
    val families = FamilyMock.familyList
    CreateFamilyContent(families)
}

@Composable
fun CreateFamilyContent(
    families: List<FamilyUiModel>
) {
    var text by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .background(KidemmaColors.Background)
            .padding(horizontal = 20.dp).padding(top = 15.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        KidemmaTextFieldWithFilter(
            value = text,
            onValueChange = { text = it },
            placeholder = "Escribe algo",
            onClickFilter = { /* Acción al hacer clic en el filtro */ },
        )

        KidemmaPrimaryButton(modifier = Modifier
            .fillMaxWidth()
            .height(65.dp), text = stringResource(
            R.string.family_screen_create_family
        )) { }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            contentPadding = PaddingValues(
                bottom = 20.dp)
        ) {
            items(families){ family ->
                KidemmaFamilyCardItem(
                    familyName = family.familyName,
                    familyNickname = family.nickname,
                    familyMembers = family.members,
                ) { }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CrateFamilyScreenPreview() {
   CreateFamilyScreen()
}