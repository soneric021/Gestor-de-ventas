package com.rikson.gestordeventas.domain.repository

import androidx.lifecycle.LiveData
import com.rikson.gestordeventas.data.entities.*
import kotlinx.coroutines.flow.Flow

interface InvoiceRepository {
    fun getInvoices():Flow<List<InvoiceWithClientAndCompany>>
    fun getInvoiceById(id:Int): Flow<InvoiceWithClientAndCompany>
    suspend fun saveInvoice(
        clientEntity: ClientEntity,
        companyEntity: CompanyEntity,
        products: List<ProductEntity>
    ):Int
}