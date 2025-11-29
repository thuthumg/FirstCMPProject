package org.example.firstcmpproject.di

import org.example.firstcmpproject.core.persistence.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { DatabaseFactory() }
    }