package com.kidemma.samplearchitect.presentation.module

import com.kidemma.samplearchitect.presentation.viewmodel.TodoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Copyright (c) 2025 Accenture. All rights reserved.
 *
 * This software is the confidential and proprietary information of Accenture.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * Android Mobility.
 * Creator: carlos.graniel
 * Created at: 25/02/2026
 *
 */

// UI: ViewModels
val uiModule = module {
    viewModel { TodoViewModel(get()) }
}
