package com.kidemma

import android.app.Application
import com.kidemma.samplearchitect.presentation.module.createAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

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

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = createAppModules()

        startKoin {
            androidContext(applicationContext)
            modules(appModules)
        }
    }
}