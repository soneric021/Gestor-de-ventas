package com.rikson.gestordeventas.di

import androidx.room.Room
import com.rikson.gestordeventas.GestorApplication
import com.rikson.gestordeventas.data.local.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder((androidApplication() as GestorApplication), AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .build()
    }
    single {
        (get() as AppDatabase).invoiceDao()
    }
}