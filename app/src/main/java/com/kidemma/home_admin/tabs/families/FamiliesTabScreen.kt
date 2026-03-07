package com.kidemma.home_admin.tabs.families

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kidemma.R
import com.kidemma.authentication.domain.model.FamilyUiModel
import com.kidemma.common.components.KidemmaBodyLarge
import com.kidemma.common.components.KidemmaPrimaryButton
import com.kidemma.common.components.KidemmaTextFieldWithFilter
import com.kidemma.common.ui.theme.KidemmaColors
import org.koin.androidx.compose.koinViewModel

/*
 * File: FamiliesTabScreen
 * Description: [Short description]
 *
 * Created by: Laura Zermeño Pichardo
 * Created on: 26/02/26
 * Last modified: 06/03/26
 */
@Composable
fun FamiliesTabScreen(
    viewModel: FamiliesTabViewModel = koinViewModel(),
    onNavigateToCreateFamily: () -> Unit,
    onNavigateToFamilyDetail: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(Modifier.fillMaxSize()) {
        FamiliesTabContent(
            familyList = state.families,
            text = state.searchQuery,
            onTextChange = {
                viewModel.onEvent(
                    FamiliesTabContract.Event.OnSearchQueryChange(it)
                )
            },
            onNavigateToCreateFamily = onNavigateToCreateFamily,
            onNavigateToFamilyDetail = onNavigateToFamilyDetail
        )

        if (state.isLoading){
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}

@Composable
fun FamiliesTabContent(
    familyList: List<FamilyUiModel>,
    text: String,
    onTextChange: (String) -> Unit,
    onNavigateToCreateFamily: () -> Unit,
    onNavigateToFamilyDetail: (Int) -> Unit
) {

    Column(
        Modifier
            .fillMaxSize()
            .background(KidemmaColors.Background)
            .padding(horizontal = 20.dp)
            .padding(top = 15.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        KidemmaTextFieldWithFilter(
            value = text,
            onValueChange = onTextChange,
            placeholder = stringResource(R.string.family_screen_write_something)
        )

        KidemmaPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp), text = stringResource(
                R.string.family_screen_create_family
            )
        ) { onNavigateToCreateFamily()}

        if (familyList.isEmpty()){
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                KidemmaBodyLarge(stringResource(R.string.family_screen_empty_list) )
            }
        }else{
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(30.dp),
                contentPadding = PaddingValues(
                    bottom = 20.dp
                )
            ) {
                items(familyList) { family ->
                    FamiliesTabCardItem(
                        familyName = family.familyName,
                        familyNickname = family.nickname,
                        familyMembers = family.members,
                    ) { onNavigateToFamilyDetail(family.id)}
                }
            }
        }

    }
}
