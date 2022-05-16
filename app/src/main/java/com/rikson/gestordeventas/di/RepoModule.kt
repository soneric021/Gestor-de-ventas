package com.rikson.gestordeventas.di

import com.rikson.gestordeventas.domain.repository.InvoiceRepository
import com.rikson.gestordeventas.data.repository.InvoiceRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<InvoiceRepository> { InvoiceRepositoryImpl(get()) }
}