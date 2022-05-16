package com.rikson.gestordeventas

import android.app.Application
import com.rikson.gestordeventas.di.databaseModule
import com.rikson.gestordeventas.di.repositoryModule
import com.rikson.gestordeventas.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GestorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GestorApplication)
            modules(
                databaseModule,
                repositoryModule,
                viewmodelModule
            )
        }
    }
}