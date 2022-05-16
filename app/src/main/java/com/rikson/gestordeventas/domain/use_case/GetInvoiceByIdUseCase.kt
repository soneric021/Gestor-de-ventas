package com.rikson.gestordeventas.domain.use_case

import com.rikson.gestordeventas.data.entities.InvoiceWithClientAndCompany
import com.rikson.gestordeventas.domain.repository.InvoiceRepository
import kotlinx.coroutines.flow.Flow

class GetInvoiceByIdUseCase(private val repository: InvoiceRepository) {
    operator fun invoke(id:Int):Flow<InvoiceWithClientAndCompany>{
        return repository.getInvoiceById(id)
    }
}