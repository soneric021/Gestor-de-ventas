package com.rikson.gestordeventas.di

import com.rikson.gestordeventas.domain.use_case.*
import com.rikson.gestordeventas.ui.form.FormViewModel
import com.rikson.gestordeventas.ui.invoices.InvoiceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    factory { DeleteInvoicesUseCase(database = get()) }
    factory { GetInvoicesUseCase(repository = get()) }
    factory { SaveInvoiceUseCase(repository = get() ) }
    factory { GetInvoiceByIdUseCase(repository = get()) }
    factory { InvoiceUseCase(
        getInvoicesUseCase = get(),
        deleteInvoicesUseCase = get(),
        saveInvoiceUseCase = get(),
        getInvoiceByIdUseCase = get()
    ) }

    viewModel{ FormViewModel(invoiceUseCase = get())}
    viewModel{ InvoiceViewModel(invoiceUseCase = get() ) }
}