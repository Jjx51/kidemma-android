package com.kidemma.home_admin.tabs.families.presentation

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kidemma.R
import com.kidemma.authentication.domain.model.FamilyUiModel
import com.kidemma.common.components.KidemmaBodyLarge
import com.kidemma.common.components.KidemmaLoadingOverlay
import com.kidemma.common.components.KidemmaPrimaryButton
import com.kidemma.common.components.KidemmaTextFieldWithFilter
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens
import com.kidemma.home_admin.tabs.families.presentation.FamiliesUiConstants.Dimens.ContentSpacing
import com.kidemma.home_admin.tabs.families.presentation.FamiliesUiConstants.Dimens.ListSpacing
import com.kidemma.home_admin.tabs.families.presentation.FamiliesUiConstants.Dimens.ScreenTopPadding
import org.koin.androidx.compose.koinViewModel

/*
 * File: FamiliesTabScreen
 * Description: screen for listing and filtering families
 *
 * Created by: Laura Zermeño Pichardo
 * Created on: 26/02/26
 * Last modified: 12/03/26
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
                viewModel.onIntent(
                    FamiliesTabContract.Intent.OnSearchQueryChange(it)
                )
            },
            onNavigateToCreateFamily = onNavigateToCreateFamily,
            onNavigateToFamilyDetail = onNavigateToFamilyDetail
        )

        if (state.isLoading){
            KidemmaLoadingOverlay()
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
            .padding(horizontal = KidemmaDimens.GeneralPaddingFillMaxSize)
            .padding(top = ScreenTopPadding),
        verticalArrangement = Arrangement.spacedBy(ContentSpacing)
    ) {
        KidemmaTextFieldWithFilter(
            value = text,
            onValueChange = onTextChange
        )

        KidemmaPrimaryButton(
            text = stringResource(R.string.family_screen_create_family),
            modifier = Modifier
                .fillMaxWidth()
                .height(KidemmaDimens.ButtonHeight)
        ) { onNavigateToCreateFamily()}

        if (familyList.isEmpty()){
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                KidemmaBodyLarge(stringResource(R.string.family_screen_empty_list) )
            }
        }else{
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(ListSpacing),
                contentPadding = PaddingValues(
                    bottom = KidemmaDimens.ListBottomPadding
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
