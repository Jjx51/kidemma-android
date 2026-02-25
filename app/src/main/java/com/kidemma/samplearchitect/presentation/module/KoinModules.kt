package com.kidemma.samplearchitect.presentation.module

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Copyright (c) 2025 Accenture. All rights reserved.
 *
 * This software is the confidential and proprietary information of Accenture.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * Android Mobility.
 * Creator: carlos.graniel
 * Created at: 24/09/2025
 *
 */
fun createAppModules(): Module = module {
    includes(networkModule, dataModule, domainModule, uiModule)
}
