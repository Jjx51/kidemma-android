package com.kidemma.core

import android.app.Application
import com.kidemma.authentication.di.authenticationModule
import com.kidemma.common.di.commonModule
import com.kidemma.common.di.databaseModule
import com.kidemma.homeAdmin.di.adminModule
import com.kidemma.introduction.di.introductionModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
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

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidContext(applicationContext)
            modules(module() {

                // Here will be the modules for feature
                includes(
                    introductionModule,
                    authenticationModule,
                    adminModule
                )

                //Here will be the common modules like network , database, etc
                includes(
                    commonModule,
                    databaseModule
                )
            })
        }
    }
}
