package com.rikson.gestordeventas.domain.use_case

import com.rikson.gestordeventas.data.local.AppDatabase
import com.rikson.gestordeventas.domain.repository.InvoiceRepository

class DeleteInvoicesUseCase(private val database: AppDatabase) {

    operator fun invoke(){
        database.clearAllTables()
    }
}