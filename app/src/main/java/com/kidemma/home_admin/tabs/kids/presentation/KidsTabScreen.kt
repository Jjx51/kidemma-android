package com.kidemma.home_admin.tabs.kids.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

/*
 * File: KidsTabScreen.kt
 * Description: Entry composable for the Kids tab screen.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

@Composable
fun KidsTabScreen(
    viewModel: KidsViewModelImpl = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    // TODO: render UI once Kids tab components are implemented
    // Using `state` here prevents it from being marked unused while screen is still WIP.
    state
}
