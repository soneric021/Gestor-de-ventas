package com.rikson.gestordeventas.domain.use_case

import com.rikson.gestordeventas.data.entities.InvoiceEntity
import com.rikson.gestordeventas.data.entities.InvoiceWithClientAndCompany
import com.rikson.gestordeventas.domain.repository.InvoiceRepository
import kotlinx.coroutines.flow.Flow

class GetInvoicesUseCase(private val repository: InvoiceRepository) {

    operator fun invoke() : Flow<List<InvoiceWithClientAndCompany>>{
        return repository.getInvoices()
    }
}