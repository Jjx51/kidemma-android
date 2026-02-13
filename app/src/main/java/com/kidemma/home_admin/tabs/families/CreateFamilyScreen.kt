package com.kidemma.home_admin.tabs.families

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.common.components.KidemmaFamilyCardItem
import com.kidemma.common.components.KidemmaPrimaryButton
import com.kidemma.common.components.KidemmaTextFieldWithFilter
import com.kidemma.common.ui.theme.KidemmaColors

@Composable
fun CreateFamilyScreen() {
    CreateFamilyContent()
}

@Composable
fun CreateFamilyContent() {
    var text by remember { mutableStateOf("") }
    Column(
        Modifier.fillMaxSize().background(KidemmaColors.Background).padding(horizontal = 20.dp, vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        KidemmaTextFieldWithFilter(
            value = text,
            onValueChange = { text = it },
            placeholder = "Escribe algo",
            onClickFilter = { /* Acción al hacer clic en el filtro */ },
        )

        KidemmaPrimaryButton(modifier = Modifier.fillMaxWidth().height(65.dp), text = "Crear familia") { }

        LazyColumn() {
            items(4){
                KidemmaFamilyCardItem(
                    familyName = "Hernandez Ramirez",
                    familyNickname = "Familia mascotas"
                ) { }
                Spacer(Modifier.height(30.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CrateFamilyScreenPreview() {
   CreateFamilyScreen()
}