package com.kidemma.common.di

import androidx.room.Room
import com.kidemma.common.data.local.KidemmaDatabase
import com.kidemma.common.domain.repository.NoteRepository
import com.kidemma.common.domain.repository.NoteRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Copyright (c) 2025 Accenture. All rights reserved.
 *
 * This software is the confidential and proprietary information of Accenture.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * Android Mobility.
 * Creator: carlos.graniel
 * Created at: 17/03/2026
 *
 */

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            KidemmaDatabase::class.java,
            KidemmaDatabase.DATABASE_NAME
        ).build()
    }

    single { get<KidemmaDatabase>().noteDao }

    singleOf(::NoteRepositoryImpl) bind NoteRepository::class
}
